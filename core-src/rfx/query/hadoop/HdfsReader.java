package rfx.query.hadoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsReader {

	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
		    conf.addResource(new Path("configs/hadoop/core-site.xml"));
		    conf.addResource(new Path("configs/hadoop/hdfs-site.xml"));
			String hdfsPath = "hdfs://localhost:54310/nguyentantrieu-info-access-logs/Nov-2013";
			Path pt = new Path(hdfsPath);
			FileSystem fs = FileSystem.get(conf);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));			
			//System.out.println(br.lines().count());
			br.lines().parallel().filter((String log)->{
				if(log.contains("Pingdom.com")){
					return false;
				}
				return true;
			}).forEach((String log)-> {
				System.out.println(log);
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
