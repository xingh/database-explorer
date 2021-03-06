/**
 * 
 */
package com.gs.dbex.application.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;

import com.gs.dbex.application.constants.ApplicationConstants;
import com.gs.dbex.application.dlg.QuickEditDialog;
import com.gs.dbex.application.dlg.ResultFilterDialog;
import com.gs.dbex.application.dlg.TableDataEditorDialog;
import com.gs.dbex.application.pagination.PaginatedTablePanel;
import com.gs.dbex.application.table.model.ResultSetTableModelFactory;
import com.gs.dbex.application.util.MenuBarUtil;
import com.gs.dbex.design.util.DesignUtil;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Table;
import com.gs.dbex.model.vo.QuickEditVO;
import com.gs.dbex.service.DbexServiceBeanFactory;
import com.gs.dbex.service.QueryExecutionService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class TableDataPanel extends JPanel implements ActionListener{

	private static final Logger logger = Logger.getLogger(TableDataPanel.class);
	
	private JButton refreshButton, addRecordButton, editRecordButton, deleteRecordButton,
		filterDataButton;
	private JTable dataTable;
	private JToolBar dataToolBar;
	private String tableName, schemaName;
	private ConnectionProperties connectionProperties;
	private String queryString;
	private String currentFilter = "";
	private JFrame parentFrame;
	
	private PaginatedTablePanel paginatedTablePanel;
	
	private QueryExecutionService queryExecutionService = DbexServiceBeanFactory.getBeanFactory().getQueryExecutionService();

	public TableDataPanel(JFrame parentFrame, Table databaseTable,
			ConnectionProperties connectionProperties) {
		this.parentFrame = parentFrame;
		this.schemaName = databaseTable.getSchemaName();
		this.tableName = databaseTable.getModelName();
		this.connectionProperties = connectionProperties;
		paginatedTablePanel = new PaginatedTablePanel(parentFrame, connectionProperties, databaseTable);
		initComponent();
		dataToolBar.setVisible(false);
	}



	private void showTableData() {
		paginatedTablePanel.getPaginationResult().setCurrentPage(1);
		paginatedTablePanel.showTableData();
		/*try {
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dataTable.setCellSelectionEnabled(true);
			dataTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
		} catch(Exception e){
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		}*/
		DesignUtil.updateTableColumnWidth(dataTable);
	}
	
	private void showTableData(String filterSubQuery) {
		paginatedTablePanel.getPaginationResult().setCurrentPage(1);
		paginatedTablePanel.showTableData(filterSubQuery);
		/*try {
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dataTable.setCellSelectionEnabled(true);
			dataTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
		} catch(Exception e){
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		}*/
		DesignUtil.updateTableColumnWidth(dataTable);
	}


	private void initComponent() {
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		Insets insets = null;
		
		refreshButton = new JButton();
		addRecordButton = new JButton();
		editRecordButton = new JButton();
		deleteRecordButton = new JButton();
		filterDataButton = new JButton();
		dataToolBar = new JToolBar();
		dataTable = new JTable();

		
		dataTable.addMouseListener(new MouseListener(){

			
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(e.getClickCount() == 2){
						/*QuickEditVO vo = new QuickEditVO();
						vo.setTableName(tableName);
						vo.setSchemaName(schemaName);
						int columnIndex = dataTable.getSelectedColumn();
						int rowIndex = dataTable.getSelectedRow();
						int columnCount = dataTable.getColumnCount();
						String q = "SELECT ROWID, ORA_ROWSCN FROM " + getSchemaName() + "." + getTableName() + " WHERE ";
						for (int i = 0; i < columnCount; i++) {
							if(dataTable.getModel().getValueAt(rowIndex, i) == null)
								continue;
							Class clazz = dataTable.getColumnClass(i);
							if(clazz.getCanonicalName().equalsIgnoreCase("java.util.Date") 
									|| clazz.getCanonicalName().equalsIgnoreCase("java.sql.Date"))
								continue;
							q += dataTable.getModel().getColumnName(i) + " = '"
								+ (
								(dataTable.getModel().getValueAt(rowIndex, i) != null)
								? dataTable.getModel().getValueAt(rowIndex, i).toString() : "") + "' ";
							if(i != columnCount-1){
								q += "AND ";
							}
						}
						Connection con = null;
						try{
							con = getConnectionProperties().getDataSource().getConnection();
							Statement stmt = con.prepareStatement(q);
							ResultSet rs = stmt.executeQuery(q);
							if(rs == null)
								return;
							while(rs.next()){
								ROWID rid = (ROWID) rs.getObject("ROWID");
								if(rid != null){
									vo.setRowid(rid.stringValue());
								}
								String x = rs.getString("ORA_ROWSCN");
								if(x != null){
									vo.setOraRowscn(x);
								}
							}
						}catch(Exception ex){
							return;
						}finally{
							if(con != null){
								try {
									con.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
						vo.setCurrentColumnName(dataTable.getModel().getColumnName(columnIndex));
						vo.setCurrentColumnValue(dataTable.getModel().getValueAt(rowIndex, columnIndex).toString());
						vo.setConnectionProperties(getConnectionProperties());
						openQuickEditDialog(vo);*/
					}
				}
			}


			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		refreshButton.setToolTipText("Refresh");
		addRecordButton.setToolTipText("Add Record");
		editRecordButton.setToolTipText("Edit Record");
		deleteRecordButton.setToolTipText("Delete Record");
		filterDataButton.setToolTipText("Apply Filter");
		
		setLayout(new GridBagLayout());
		
		dataToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(ApplicationConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.addActionListener(this);
		refreshButton.setFocusable(false);
		dataToolBar.add(refreshButton);
		dataToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(ApplicationConstants.IMAGE_PATH
						+ "add_plus.png"));
		addRecordButton.setIcon(image);
		addRecordButton.setFocusable(false);
		addRecordButton.addActionListener(this);
		dataToolBar.add(addRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(ApplicationConstants.IMAGE_PATH
						+ "editor_area.gif"));
		editRecordButton.setIcon(image);
		editRecordButton.addActionListener(this);
		editRecordButton.setFocusable(false);
		dataToolBar.add(editRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(ApplicationConstants.IMAGE_PATH
						+ "delete_edit.gif"));
		deleteRecordButton.setIcon(image);
		deleteRecordButton.setFocusable(false);
		dataToolBar.add(deleteRecordButton);
		dataToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(ApplicationConstants.IMAGE_PATH
						+ "systemfilterpool.gif"));
		filterDataButton.setIcon(image);
		filterDataButton.setFocusable(false);
		filterDataButton.addActionListener(this);
		dataToolBar.add(filterDataButton);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(dataToolBar, gridBagConstraints);

		
		
		dataTable.setAutoCreateRowSorter(true);
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dataTable.setAutoscrolls(true);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		insets = new Insets(1,1,1,1);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = insets;
		
		
		//add(new JScrollPane(dataTable), gridBagConstraints);
		add(paginatedTablePanel, gridBagConstraints);

	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}


	public String getSchemaName() {
		return schemaName;
	}


	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}


	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource().equals(refreshButton)){
			showTableData();
		} if(evt.getSource().equals(addRecordButton)){
			addNewRecord();
		} else if(evt.getSource().equals(filterDataButton)){
			applyFilter();
		} else if(evt.getSource().equals(editRecordButton)){
			editRecord();
		} 
	}


	/**
	 * 
	 */
	public void addNewRecord() {
		TableDataEditorDialog dataEditorDialog = new TableDataEditorDialog(getParentFrame(), dataTable);
		dataEditorDialog.setSchemaName(schemaName);
		dataEditorDialog.setTableName(tableName);
		dataEditorDialog.setConnectionProperties(connectionProperties);
		dataEditorDialog.setLocation(100, 100);
		int opt = dataEditorDialog.showEditorDialog();
		if(opt == ApplicationConstants.APPLY_OPTION){
			
		}
		
	}



	private void applyFilter() {
		ResultFilterDialog filterDialog = new ResultFilterDialog(getParentFrame(), 
				true, connectionProperties.getConnectionName());
		filterDialog.setFilterQuery(currentFilter);
		filterDialog.setInputQuery(queryString);
		filterDialog.setAlwaysOnTop(true);
		filterDialog.setLocation(100, 100);
		int opt = filterDialog.showFilterDialog();
		if(opt == ApplicationConstants.APPLY_OPTION){
			currentFilter  = filterDialog.getFilterQuery();
			showTableData(currentFilter);
		}
	}

	



	public void editRecord(){
		TableDataEditorDialog dataEditorDialog = new TableDataEditorDialog(getParentFrame(), dataTable);
		dataEditorDialog.setSchemaName(schemaName);
		dataEditorDialog.setTableName(tableName);
		dataEditorDialog.setConnectionProperties(connectionProperties);
		dataEditorDialog.setLocation(100, 100);
		int opt = dataEditorDialog.showEditorDialog();
		if(opt == ApplicationConstants.APPLY_OPTION){
			
		}
	}
	
	public void openQuickEditDialog(QuickEditVO vo) {
		QuickEditDialog editDialog = new QuickEditDialog(getParentFrame(), vo);
		int opt = editDialog.showDialog();
		if(opt == ApplicationConstants.APPLY_OPTION){
			showTableData();
		}
	}

	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

}
