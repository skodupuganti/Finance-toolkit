package com.android.personalfinancetoolkit;

public class Constructor 
{
	private String item;
	Long rowid;
	String price;
	
	public Constructor(Long rowid,String item,String price)
	{
		this.rowid=rowid;
		this.item=item;
		this.price=price;
	}
	public Long rowid()
	{
		return rowid;
	}
	public String item()
	{
		return item;
	}
	public String price()
	{
		return price; 
	}
}
