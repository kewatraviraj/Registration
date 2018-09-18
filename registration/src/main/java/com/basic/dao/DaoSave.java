/**
 * 
 */
package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;
import com.basic.pojo.Address;
import com.basic.pojo.Filemap;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * @author HP
 *
 */
public interface DaoSave {
	
	public int saveuser(User Value1) throws ClassNotFoundException, SQLException, IOException;
	public boolean saveaddress(Address Value2) throws ClassNotFoundException, SQLException, IOException;
	public int savefile(Files Value3) throws ClassNotFoundException, SQLException, IOException;
	public boolean savefilemap(Filemap Value4)throws ClassNotFoundException, SQLException, IOException;
	
}
