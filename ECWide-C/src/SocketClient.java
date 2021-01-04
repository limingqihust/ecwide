import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketClient implements Runnable {
  private int nodeId;
  private ServerInfo[] targets;
  private SocketChannel[] outc;
  private ObjectOutputStream[] objOuts;

  public SocketClient() {
    this.nodeId = 1;
    setTargets(new ServerInfo[] { new ServerInfo() });
  }

  public SocketClient(int nodeId, ServerInfo[] targets, SocketChannel[] outc) {
    this.nodeId = nodeId;
    this.outc = outc;
    setTargets(targets);
  }

  public void setTargets(ServerInfo[] targets) {
    this.targets = targets;
    if (nodeId == 0) {
      objOuts = new ObjectOutputStream[targets.length];
    }
  }

  private void connetTargets() throws IOException, InterruptedException {
    ByteBuffer buffer = ByteBuffer.allocate(4);
    buffer.putInt(nodeId);
    for (int i = 1; i < targets.length; ++i) {
      if (i == nodeId) {
        continue;
      }
      // boolean print_flag = true;
      while (true) {
        try {
          outc[i] = SocketChannel.open();
          outc[i].connect(new InetSocketAddress(targets[i].ip, targets[i].port));
        } catch (SocketException e) {
          // if (print_flag) {
          // System.out.printf("trying to reconnect node %d ...\n", i);
          // }
          // print_flag = false;
          TimeUnit.MILLISECONDS.sleep(1);
        }
        if (outc[i] != null && outc[i].isConnected()) {
          // System.out.println("connect to node" + i + " OK");
          break;
        }
      }
      buffer.clear();
      outc[i].write(buffer);
      if (nodeId == 0) {
        objOuts[i] = new ObjectOutputStream(outc[i].socket().getOutputStream());
      }
    }
  }

  public void sendTask(int id, ECTask task) throws IOException {
    objOuts[id].writeObject(task);
  }

  private void sayGoodBye() throws IOException {
    ECTask bye = new ECTask();
    for (int i = 1; i < targets.length; ++i) {
      if (i == nodeId) {
        continue;
      }
      objOuts[i].writeObject(bye);
    }
  }

  @Override
  public void run() {
    try {
      connetTargets();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      if (nodeId == 0) {
        sayGoodBye();
      }
      for (int i = 1; i < targets.length; ++i) {
        if (i == nodeId) {
          continue;
        }
        outc[i].close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println("SocketClient close");
  }
}
