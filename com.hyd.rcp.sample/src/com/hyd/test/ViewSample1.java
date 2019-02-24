package com.hyd.test;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

public class ViewSample1 extends ViewPart {

	public static final String ID = "com.hyd.test.ViewSample1"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_TopLeft;
	private Table table_TopCenter;
	private Table table_TopRight;

	public ViewSample1() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		StyledText styledText = new StyledText(container, SWT.BORDER);
		
		ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		
		ViewForm viewForm = new ViewForm(parent, SWT.BORDER);
		
		table_TopLeft = new Table(viewForm, SWT.BORDER | SWT.FULL_SELECTION);
		viewForm.setTopLeft(table_TopLeft);
		table_TopLeft.setHeaderVisible(true);
		table_TopLeft.setLinesVisible(true);
		
		TableViewer tableViewer_1 = new TableViewer(viewForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_TopCenter = tableViewer_1.getTable();
		viewForm.setTopCenter(table_TopCenter);
		
		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(viewForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_TopRight = checkboxTableViewer.getTable();
		viewForm.setTopRight(table_TopRight);
		
		ComboViewer comboViewer = new ComboViewer(viewForm, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		viewForm.setContent(combo);
		
		TableViewer tableViewer = new TableViewer(viewForm, SWT.BORDER | SWT.FULL_SELECTION);
		viewForm.setContent(table_1);
		table_1 = tableViewer.getTable();

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
