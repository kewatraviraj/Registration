/**
 * 
 */
package com.basic.dao;

import com.basic.pojo.Address;
import com.basic.pojo.Filemap;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * @author HP
 *
 */
public interface DaoSave {
	
	int saveuser(User Value1);
	boolean saveaddress(Address Value2);
	int savefile(Files Value3);
	boolean savefilemap(Filemap Value4);
	
}
