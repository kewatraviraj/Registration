package com.basic.pojo;

import java.io.InputStream;

public class Files {
	
	private int file_id;
	private String file_type;
	private InputStream file;
	private String created_time;
	private int update_by;
	private String update_time;
	private String filestring;
	
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	public InputStream getFile() {
		return file;
	}
	public void setFile(InputStream file) {
		this.file = file;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public int getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(int update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getFilestring() {
		return filestring;
	}
	public void setFilestring(String filestring) {
		this.filestring = filestring;
	}
	
	
}
