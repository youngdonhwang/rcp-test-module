package com.hyd.test;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class ViewSample extends ViewPart {

	public static final String ID = "com.hyd.test.ViewSample"; //$NON-NLS-1$
	private Text text;

	public ViewSample() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(35, 37, 76, 25);
		btnNewButton.setText("New Button");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(38, 80, 271, 193);

//		createActions();
//		initializeToolBar();
//		initializeMenu();
		System.out.println("open window");
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
