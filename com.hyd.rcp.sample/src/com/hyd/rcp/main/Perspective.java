package com.hyd.rcp.main;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		addPerspectiveShortcuts(layout);
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		IFolderLayout folderLayout = layout.createFolder("folder_1", IPageLayout.RIGHT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		folderLayout.addView("com.hyd.test.ViewSample");
		folderLayout.addView("com.hyd.test.ViewSample1");
		folderLayout.addView("com.hyd.test.ViewSample2");
		
	}

	private void addPerspectiveShortcuts(IPageLayout layout) {
		layout.addPerspectiveShortcut("com.hyd.rcp.test2.perspective");
	}
}
