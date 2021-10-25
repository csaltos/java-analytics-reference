package analytics;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

	
	
	public UserApiController() throws IOException {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.masters", "esmeralda.csaltos.com:16000");
		Connection connection = ConnectionFactory.createConnection(config);
		Admin admin = connection.getAdmin();
		TableDescriptor table1 = TableDescriptorBuilder.newBuilder(TableName.valueOf("Table2"))
			.setColumnFamily(ColumnFamilyDescriptorBuilder.of("ColumnFamily2")).build();
		admin.createTable(table1);		
	}
}
