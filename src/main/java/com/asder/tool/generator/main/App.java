package com.asder.tool.generator.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.asder.tool.generator.mybatisplus.DriverName;
import com.asder.tool.generator.mybatisplus.Generator;
import com.asder.tool.generator.utils.MyPrintStream;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.Preconditions;

/**
 * Hello world!
 *
 */
public class App 
{
	// 用以检验当前输入数据源类型是否支持
	@SuppressWarnings("serial")
	private static Set<String> dbSourceTypeSet = new HashSet<String>(){{
		
		for(DbType type : DbType.values()) {
			add(type.getValue());
		}
	}};
	
	// 用以检验当前输入JDBC驱动名称是否支持
	@SuppressWarnings("serial")
	private static Set<String> driverNameTypeSet = new HashSet<String>(){{
		
		for(DriverName type : DriverName.values()) {
			add(type.getValue());
		}
	}};
	
	public static void main(String[] args) {
		
		if(args.length == 0) {
			
			// 图形界面模式
			guiMode();
		} else {
			
			// 命令行模式
			consoleMode(args);
		}
	}
	
    private static void exec(String outputDir, String author, String dbType, String driverName, String url, 
                             String username, String password, NamingStrategy naming, String includeTablename) throws Exception {
    	
    	System.out.println("=== Processing... ===");
    	
		// 执行
    	Generator.run(outputDir, author, Enum.valueOf(DbType.class, dbType.trim().toUpperCase()), 
    									 driverName, url, username, password, naming, includeTablename);

		System.out.println("=== Execution Successful! ===");
    }
	
	public static void consoleMode(String[] args) {
		
		// 输入参数校验
		Preconditions.checkArgument(args.length == 9 || args.length == 8, "Usage -> MybatisPlusGeneratorTool.jar outputDir, author, dbType, driverName, url, username, password, naming, includeTablename "
				+ "-> F://temp user mysql com.mysql.jdbc.Driver jdbc:mysql://localhost:3306/vcloud?useUnicode=true&amp;characterEncoding=UTF-8&amp;generateSimpleParameterMetadata=true root admin123 underline_to_camel vcloud_device");

		String outputDir = args[0];
		String author = args[0];
		String dbType = args[0];
		String driverName = args[0];
		String url = args[0];
		String username = args[0];
		String password = args[0];
		String naming = args[0];
		String includeTablename = args[0];
		
		Preconditions.checkArgument(dbSourceTypeSet.contains(dbType), "dbSourceTypeSet is not supported! Usage -> [mysql, oracle, sql_server, postgre_sql]");
		Preconditions.checkArgument(driverNameTypeSet.contains(driverName), "driverNameTypeSet is not supported! Usage -> [com.mysql.jdbc.Driver, oracle.jdbc.driver.OracleDriver, com.microsoft.jdbc.sqlserver.SQLServerDriver, org.postgresql.Driver]");
		
		try {
			
			// 执行
			exec(outputDir, author, dbType, driverName, url, 
				 username, password, Enum.valueOf(NamingStrategy.class, naming.trim().toUpperCase()), includeTablename);			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void guiMode() {
		
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Mybatis Plus Generator Tool");
        frame.setSize(390, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        
        // 调用用户定义的方法并添加组件到面板
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
	}
	
	static int interval = 20;
	static int count = 0;
	private static int getInterval() {
		
		if(2 == count++) {
			interval += 30;
			count = 1;
		}
		
		return interval;
	}
	
    private static void placeComponents(JPanel panel) {
    	
        /* 
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel      
        JLabel outputDirLabel = new JLabel("输出路径:");
        outputDirLabel.setBounds(10,getInterval(),80,25);
        panel.add(outputDirLabel);

        final JTextField outputDirText = new JTextField(20);
        outputDirText.setBounds(100,getInterval(),265,25);
        panel.add(outputDirText);
        outputDirText.setText("F://temp");

        JLabel authorLabel = new JLabel("作者:");
        authorLabel.setBounds(10,getInterval(),80,25);
        panel.add(authorLabel);

        final JTextField authorText = new JTextField(20);
        authorText.setBounds(100,getInterval(),265,25);
        panel.add(authorText);
        authorText.setText("user");
        
        JLabel dbTypeLabel = new JLabel("数据源类型:");
        dbTypeLabel.setBounds(10,getInterval(),80,25);
        panel.add(dbTypeLabel);

        final JComboBox<?> dbTypeCombo = new JComboBox<Object>(dbSourceTypeSet.toArray());
        dbTypeCombo.setBounds(100,getInterval(),165,25);
        panel.add(dbTypeCombo);
        dbTypeCombo.setSelectedIndex(2);
        
        JLabel driverTypeLabel = new JLabel("JDBC驱动:");
        driverTypeLabel.setBounds(10,getInterval(),80,25);
        panel.add(driverTypeLabel);

        final JComboBox<?> driverNameCombo = new JComboBox<Object>(driverNameTypeSet.toArray());
        driverNameCombo.setBounds(100,getInterval(),165,25);
        panel.add(driverNameCombo);
        driverNameCombo.setSelectedIndex(1);
        
        JLabel urlLabel = new JLabel("URL:");
        urlLabel.setBounds(10,getInterval(),80,25);
        panel.add(urlLabel);

        final JTextField urlText = new JTextField(20);
        urlText.setBounds(100,getInterval(),165,25);
        panel.add(urlText);
        urlText.setText("jdbc:mysql://localhost:3306/vcloud?useUnicode=true&amp;characterEncoding=UTF-8&amp;generateSimpleParameterMetadata=true");
        
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setBounds(10,getInterval(),80,25);
        panel.add(usernameLabel);

        final JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,getInterval(),165,25);
        panel.add(usernameText);
        usernameText.setText("root");
        
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,getInterval(),80,25);
        panel.add(passwordLabel);

        final JTextField passwordText = new JTextField(20);
        passwordText.setBounds(100,getInterval(),165,25);
        panel.add(passwordText);
        passwordText.setText("admin123");
        
        JLabel policyLabel = new JLabel("表名生成策略:");
        policyLabel.setBounds(10,getInterval(),110,25);
        panel.add(policyLabel);
        
        NamingStrategy[] namingItem = {NamingStrategy.nochange, NamingStrategy.underline_to_camel};
        final JComboBox<?> namingCombo = new JComboBox<Object>(namingItem);
        namingCombo.setBounds(100,getInterval(),165,25);
        panel.add(namingCombo);
        namingCombo.setSelectedIndex(1);
        
        JLabel includetablenameLabel = new JLabel("包含表名:");
        includetablenameLabel.setBounds(10,getInterval(),80,25);
        panel.add(includetablenameLabel);

        final JTextField includeTablenameText = new JTextField(20);
        includeTablenameText.setBounds(100,getInterval(),165,25);
        panel.add(includeTablenameText);
        includeTablenameText.setText("");

        // 创建按钮
        JButton button = new JButton("生成");
        button.setBounds(10, getInterval(), 80, 25);
        panel.add(button);
        button.addActionListener(new ActionListener() { 

            @Override 
            public void actionPerformed(ActionEvent e) { 

        		String outputDir = outputDirText.getText();
        		String author = authorText.getText();
        		String dbType = dbTypeCombo.getSelectedItem().toString();
        		String driverName = driverNameCombo.getSelectedItem().toString();
        		String url = urlText.getText();
        		String username = usernameText.getText();
        		String password = passwordText.getText();
        		NamingStrategy naming = (NamingStrategy)namingCombo.getSelectedItem();
        		String includeTablename = includeTablenameText.getText();
        		
        		try {
        			
					exec(outputDir, author, dbType, driverName, url, 
						 username, password, naming, includeTablename);
					
				} catch (Exception ex) {
					
					ex.printStackTrace();
				}
            } 

        });
        
        JTextArea consoleArea = new JTextArea();
		consoleArea.setWrapStyleWord(true); // 激活断行不断字功能
		
		JScrollPane jscrollPane = new JScrollPane(consoleArea);
		jscrollPane.setBounds(0, interval+30, 1000, 600);
		panel.add(jscrollPane);

        MyPrintStream mps = new MyPrintStream(System.out, consoleArea);  
        System.setOut(mps);  
        System.setErr(mps);
    }
}
