package com.ethan.plugins;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.plugin.java.JavaPlugin;

import dnl.utils.text.table.TextTable;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		  loadConfiguration();
		     System.out.print("[MySQL] MySQL Minecraft Connector Enabled!");
	}
	
	@Override
	public void onDisable(){
		
	}
	public void loadConfiguration(){
		 String path = "ip";
		 String path2 = "database";
		 String path3 = "username";
		 String path4 = "password";
		getConfig().addDefault(path, "");
		getConfig().addDefault(path2, "");
		getConfig().addDefault(path3, "");
		getConfig().addDefault(path4, "");
	
			    saveConfig();
	    getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
	     //Save the config whenever you manipulate it
	    saveConfig();
	    reloadConfig();
	}
	@Override
    public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
if (command.getName().equalsIgnoreCase("connect")) {
    if(sender.hasPermission("sqlpermission"))   { 	
	if(args.length == 0){
        		sender.sendMessage(ChatColor.RED +"Usage: /connect [ip] [database] [username] [password]");
        	}
        	
        	else{
        		try {
        			Class.forName("com.mysql.jdbc.Driver");
        		
        			String url = "jdbc:mysql://"+ args[0] +":3306/" + args[1]; 
        		      Connection conn = DriverManager.getConnection(url, args[2], args[3]);
        		      Statement stmt = null;
        		      ResultSet rs = null;
        		      try {
        		    	    stmt = conn.createStatement();
        		    	   
        		    	  getConfig().set("ip", args[0]);
        		    	  getConfig().set("database", args[1]);
        		    	  getConfig().set("username", args[2]);
        		    	  getConfig().set("password", args[3]);
        		    	  saveConfig();
        		  	    reloadConfig();

        		  	    		System.out.println("Successfully connected to MySQL server '" + args[0] + "' under database '" + args[1] + "' using the username '" + args[2] + "'.");
        		  	    		sender.sendMessage("Successfully connected to MySQL server '" + args[0] + "' under database '" + args[1] + "' using the username '" + args[2] + "'.");

        		    	    // Now do something with the ResultSet ....
        		    	}
        		    	catch (SQLException ex){
        		    	    // handle any errors
        		    	    System.out.println("SQLException: " + ex.getMessage());
        		    	    System.out.println("SQLState: " + ex.getSQLState());
        		    	    System.out.println("VendorError: " + ex.getErrorCode());
        		    	}
        		    	    
        		    	}catch (Exception e) {

        		      System.err.println(e.getStackTrace());
        		
        		
        	}

        		
        	}
        	
        	
        	
        	return true;
            
        }
    else{
    	sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
    }
}

if (command.getName().equalsIgnoreCase("create")) {    
	if(sender.hasPermission("sqlpermission"))   { 	
	
	if(args.length == 0){
		sender.sendMessage(ChatColor.RED +"Usage: /create [tablename] [name_valtype] [name_valtype] [name_valtype] [name_valtype]");
	}
	try {
		Class.forName("com.mysql.jdbc.Driver");
	String ip = getConfig().getString("ip");
	String database = getConfig().getString("database");
	String username = getConfig().getString("username");
	String password = getConfig().getString("password");
		String url = "jdbc:mysql://"+ ip +":3306/" + database; 
	      Connection conn = DriverManager.getConnection(url, username, password);
	      Statement stmt = null;
	      ResultSet rs = null;
	      try {
	    	
	    	  
	    	 if(args.length == 2){ 
	    		 String para1 = args[1].split("_")[0];
		    	  String para2 = args[1].split("_")[1];
	    		 stmt = conn.createStatement();
	    	   stmt.executeQuery("USE " + database + ";");
	    	 stmt.executeUpdate("CREATE TABLE " + args[0]+  " (" + para1+ " " + para2 + ")" + ";");  
	    	 
	    	 System.out.println("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
	    	 sender.sendMessage("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
	    	
	    	 }
	    	 else if(args.length == 3){ 
	    		 String para1 = args[1].split("_")[0];
		    	  String para2 = args[1].split("_")[1];
		    	  String para3 = args[2].split("_")[0];
		    	  String para4 = args[2].split("_")[1];
	    		 stmt = conn.createStatement();
		    	   stmt.executeQuery("USE " + database + ";");
		    	 stmt.executeUpdate("CREATE TABLE " + args[0]+  " (" + para1+ " " + para2 + ", " + para3 + " " + para4 + ");");  
		    	 System.out.println("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
		    	 sender.sendMessage("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
	    	 }
		    	 
	    	 else if(args.length == 4){ 
	    		 String para1 = args[1].split("_")[0];
		    	  String para2 = args[1].split("_")[1];
		    	  String para3 = args[2].split("_")[0];
		    	  String para4 = args[2].split("_")[1];
		    	  String para5 = args[3].split("_")[0];
		    	  String para6 = args[3].split("_")[1];
	    		 stmt = conn.createStatement();
		    	   stmt.executeQuery("USE " + database + ";");
		    	 stmt.executeUpdate("CREATE TABLE " + args[0]+  " (" + para1+ " " + para2 + ", " + para3 + " " + para4 + ", " + para5 + " " + para6 +");"); 
		    	 System.out.println("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
		    	 sender.sendMessage("Successfully created table '" + args[0] + "' under the database '" + database + "'. ");
	    	 }
	    	 else if(args.length == 5){ 
	    		 String para1 = args[1].split("_")[0];
		    	  String para2 = args[1].split("_")[1];
		    	  String para3 = args[2].split("_")[0];
		    	  String para4 = args[2].split("_")[1];
		    	  String para5 = args[3].split("_")[0];
		    	  String para6 = args[3].split("_")[1];
		    	  String para7 = args[4].split("_")[0];
		    	  String para8 = args[4].split("_")[1];
	    		 stmt = conn.createStatement();
		    	   stmt.executeQuery("USE " + database + ";");
		    	 stmt.executeUpdate("CREATE TABLE " + args[0]+  " (" + para1+ " " + para2 + ", " + para3 + " " + para4 + ", " + para5 + " " + para6 + ", " + para7 + " " + para8 +");"); 
		    	 System.out.println("Successfully created table " + args[0] + " under the database '" + database + "'. ");
	    	 sender.sendMessage("Successfully created table " + args[0] + " under the database '" + database + "'. ");
	    	 }
	    	 else if(args.length >= 6){
	    		 sender.sendMessage(ChatColor.RED + "That many columns have not been supported yet.");
	    	 
	    	 }
	      } 

	      finally{
	    	
		    	 }
	    	    // Now do something with the ResultSet ....
	    	}
	    	catch (SQLException ex){
	    	    // handle any errors
	    		ex.printStackTrace();
	    	}
	    	    
	    	catch (Exception e) {

	    		e.printStackTrace();
	
	
}

	

	
	return true;
}
	else{
    	sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
    }
}
	if(command.getName().equalsIgnoreCase("setvalue")){
		if(sender.hasPermission("sqlpermission"))   {
		if(args.length == 0){
			sender.sendMessage(ChatColor.RED +"Usage: /setvalue [tablename] [value] [value] [value] [value]");
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String ip = getConfig().getString("ip");
			String database = getConfig().getString("database");
			String username = getConfig().getString("username");
			String password = getConfig().getString("password");
				String url = "jdbc:mysql://"+ ip +":3306/" + database; 
			      Connection conn = DriverManager.getConnection(url, username, password);
			      Statement stmt = null;
			try{
				if(args.length == 2){ 
			    	  stmt = conn.createStatement();
			    	   stmt.executeQuery("USE " + database + ";");
			    	 stmt.executeUpdate("INSERT INTO " + args[0]+  " VALUE('" + args[1] + "')" + ";");  
			    	 
			    	 System.out.println("Successfully inserted '" + args[1] + "' into the table '" + args[0] + "'. ");
			    	 sender.sendMessage("Successfully inserted '" + args[1] + "' into the table '" + args[0] + "'. ");

			    	
			    	 }
				if(args.length == 3){ 
			    	  stmt = conn.createStatement();
			    	   stmt.executeQuery("USE " + database + ";");
			    	 stmt.executeUpdate("INSERT INTO " + args[0]+  " VALUE('" + args[1] + "', '" + args[2] + "')" + ";");  
			    	 
			    	 System.out.println("Successfully inserted '" + args[1] + "' and " + args[2] +" into the table '" + args[0] + "'. ");
			    	 sender.sendMessage("Successfully inserted '" + args[1] + "' and " + args[2] +" into the table '" + args[0] + "'. ");

			    	
			    	 }
				    	 
				if(args.length == 4){ 
			    	  stmt = conn.createStatement();
			    	   stmt.executeQuery("USE " + database + ";");
			    	 stmt.executeUpdate("INSERT INTO " + args[0]+  " VALUE('" + args[1] + "', '" + args[2] + "', '" + args[3] +"')" + ";");  
			    	 
			    	 System.out.println("Successfully inserted '" + args[1] + "' and '" + args[2] + "' and '"  + args[3] +"' into the table '" + args[0] + "'. ");
			    	 sender.sendMessage("Successfully inserted '" + args[1] + "' and '" + args[2] + "' and '"  + args[3] +"' into the table '" + args[0] + "'. ");
			    	
			    	 }
				if(args.length == 5){ 
			    	  stmt = conn.createStatement();
			    	   stmt.executeQuery("USE " + database + ";");
			    	 stmt.executeUpdate("INSERT INTO " + args[0]+  " VALUE('" + args[1] + "', '" + args[2] + "', '" + args[3] +"', '" + args[4] +"')" + ";");  
			    	 
			    	 System.out.println("Successfully inserted '" + args[1] + "' and '" + args[2] + "' and '"  + args[3] +"' and '"  + args[4] +"' into the table '" + args[0] + "'. ");
			    	 sender.sendMessage("Successfully inserted '" + args[1] + "' and '" + args[2] + "' and '"  + args[3] +"' and '"  + args[4] +"' into the table '" + args[0] + "'. ");
			    	
			    	 }    	 
			if(args.length >= 6){
				sender.sendMessage(ChatColor.RED + "Inserting that many values have not been supported yet.");
			}
			    	 
			    	 }
		
		catch(Exception e){
			e.printStackTrace();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
		else{
	    	sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
	    }
	}
	if (command.getName().equalsIgnoreCase("readtable")) {
	    if(sender.hasPermission("sqlpermission"))   { 
	    	
				try {
					Class.forName("com.mysql.jdbc.Driver");
				
				String ip = getConfig().getString("ip");
				String database = getConfig().getString("database");
				String username = getConfig().getString("username");
				String password = getConfig().getString("password");
					String url = "jdbc:mysql://"+ ip +":3306/" + database; 
				      Connection conn = DriverManager.getConnection(url, username, password);
				      Statement stmt = null;
				
	    	if(args.length == 0){
				sender.sendMessage(ChatColor.RED +"Usage: /readtable [tablename] [valuename] [valuename] [valuename] [valuename]");
			}
	    	if(args.length == 1){
				sender.sendMessage(ChatColor.RED +"Usage: /readtable [tablename] [valuename] [valuename] [valuename] [valuename]");
			}
	    	if(args.length == 2){
	    		if(args[1].equals("all")){
	    			
	    				      
	    				      stmt = conn.createStatement();
	   			    	   stmt.executeQuery("USE " + database + ";");
	   			    	ResultSet rs =  stmt.executeQuery("SELECT "+ args[1] +" FROM " + args[0] + ";");  
	   			    	if(rs.next()){
	   			    		sender.sendMessage(rs.getString(1) + ", " + rs.getString(2)+ ", " + rs.getString(3)+ ", " + rs.getString(4));
				    		}
	   			    	
	    			}
	    		else if(!args[1].equals("all")){
	    			Object data[][];
				      stmt = conn.createStatement();
			    	   stmt.executeQuery("USE " + database + ";");
			    	   ResultSet rs =  stmt.executeQuery("SELECT "+ args[1] +" FROM " + args[0] + ";");  
			    	   String col[] = {args[1]};
			    	   while(rs.next()){
			    		   ResultSetMetaData rsmd = rs.getMetaData();
			    		   int columnCount = rsmd.getColumnCount();

			    		   // The column count starts from 1
			    		   for (int i = 1; i <= columnCount; i++ ) {
			    			  
							 data  = new Object[][]{{rs.getString(i)},};
							    
							 TextTable tt = new TextTable(col, data);
							 String print =tt.toString();
							 sender.sendMessage(print);
			    		     
			    		   }
			    		   
			    	  
			    	   }
			    	   
			}
	    		
	    	}
	    	
				}
	    	catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    
	    }
	}
	return false;
	
}
	
}
	

