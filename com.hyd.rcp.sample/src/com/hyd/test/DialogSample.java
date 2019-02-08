package com.hyd.test;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

public class DialogSample extends ViewPart {
//public class DialogSample extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DialogSample(Shell parentShell) {
		super();
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
/*	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		return container;
	}*/

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
/*	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}*/

	/**
	 * Return the initial size of the dialog.
	 */
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
