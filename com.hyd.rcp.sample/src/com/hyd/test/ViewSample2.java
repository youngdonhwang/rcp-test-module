package com.hyd.test;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class ViewSample2 extends ViewPart {

	public static final String ID = "com.hyd.test.ViewSample2"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_2;
	private Table table_3;
	private Table table_4;
	private Table table_5;
	private Table table_6;

	public ViewSample2() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		{
			SashForm sashForm = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
			Composite container = new Composite(sashForm, SWT.BORDER);
			{
				Composite composite = new Composite(sashForm, SWT.BORDER);
				composite.setLayout(new GridLayout(2, false));
				
				TabFolder tabFolder = new TabFolder(composite, SWT.BORDER);
				GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
				gd_tabFolder.widthHint = 574;
				tabFolder.setLayoutData(gd_tabFolder);
				
				TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
				tbtmNewItem.setText("New Item");
				
				TableViewer tableViewer = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
				table = tableViewer.getTable();
				tbtmNewItem.setControl(table);
				
				Button button = new Button(tabFolder, SWT.NONE);
				tbtmNewItem.setControl(button);
				
				TableViewer tableViewer_2 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
				table_2 = tableViewer_2.getTable();
				tbtmNewItem.setControl(table_2);
				button.setText("tab1 Button");
				
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("New Item");
				
				Button btnNewButton = new Button(tabFolder, SWT.NONE);
				tabItem.setControl(btnNewButton);
				
				TableViewer tableViewer_1 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
				table_1 = tableViewer_1.getTable();
				tabItem.setControl(table_1);
				btnNewButton.setText("tab2 Button");
				
				Button btnNewButton_1 = new Button(composite, SWT.NONE);
				btnNewButton_1.setText("New Button");
			}
			
			Composite composite = new Composite(sashForm, SWT.NONE);
			composite.setLayout(null);
			
			CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
			tabFolder.setBounds(0, 0, 590, 110);
			tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			
			CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
			tbtmNewItem_1.setText("New Item");
			
			TableViewer tableViewer = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			table_3 = tableViewer.getTable();
			tbtmNewItem_1.setControl(table_3);
			
			TableViewer tableViewer_1 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			table_4 = tableViewer_1.getTable();
			tbtmNewItem_1.setControl(table_4);
			
			TableViewer tableViewer_2 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			table_5 = tableViewer_2.getTable();
			tbtmNewItem_1.setControl(table_5);
			
			CTabItem tbtmNewItem_2 = new CTabItem(tabFolder, SWT.NONE);
			tbtmNewItem_2.setText("New Item");
			
			TableViewer tableViewer_3 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			table_6 = tableViewer_3.getTable();
			tbtmNewItem_2.setControl(table_6);
			
			Button button = new Button(tabFolder, SWT.NONE);
			tbtmNewItem_2.setControl(button);
			button.setText("New Button");
			
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("New Item");
			
			CTabFolder tabFolder_1 = new CTabFolder(composite, SWT.BORDER);
			tabFolder_1.setSize(584, 85);
			tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			
			CTabItem tbtmNewItem_3 = new CTabItem(tabFolder_1, SWT.NONE);
			tbtmNewItem_3.setText("New Item");
			
			CTabItem tbtmNewItem_4 = new CTabItem(tabFolder_1, SWT.NONE);
			tbtmNewItem_4.setText("New Item");
			sashForm.setWeights(new int[] {235, 222, 228});
		}

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
