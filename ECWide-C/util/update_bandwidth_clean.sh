host_num=70
host_hostname=node     
host_username=root                  
  

for ((i=1;i<=$host_num;i++));do
{    
	if [[ $i -ge 0 && $i -lt 10 ]]
	then
		host=${host_hostname}0${i}
	else
		host=${host_hostname}$i
	fi
	echo ${host}##############################################################################
	ssh ${host_username}@${host} "cd /root/ecwide/ECWide-C; bash util/limit_bandwidth.sh clean"
} 
done
