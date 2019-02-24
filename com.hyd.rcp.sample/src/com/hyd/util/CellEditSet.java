package com.hyd.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.w3c.dom.Element;

import com.hyd.ClassSet.Person;
//import com.hyd.rcp.test2.View.AbstractEditingSupport;

public class CellEditSet {
	
	final static String GROUP_ATTR_NAME = "dataset"; // ? 원본 "gtype" 이였음. why
	final static String GROUP_FIELD = "field";
	final static String GROUP_DATASET_SINGLE = "dataset single";
	final static String GROUP_DATASET_MULTI = "dataset multi";
	
	/*
	 *  edit setting
	 */
	private void coledit(TableViewer viewer, TableViewerColumn column) {
		column.setEditingSupport(new EditingSupport(viewer) {
			protected boolean canEdit(Object element) {
				System.out.println("canEdit");
				
				return true;
			}
			protected CellEditor getCellEditor(Object element) {
				System.out.println("getcelleditor");
				TextCellEditor textCellEditor1 = new TextCellEditor(viewer.getTable());
				return textCellEditor1;
//				return null;
			}
			protected Object getValue(Object element) {
				// TODO Auto-generated method stub
				Person input = (Person) element;
				System.out.println("getvalue");
				System.out.println(input);
				return input.getGender();
//				return null;
			}
			protected void setValue(Object element, Object value) {
				System.out.println("setvalue");
				System.out.println(element + " " + value);
				Person input = (Person) element;
				input.setGender(String.valueOf(value));
				
//				viewer.refresh();
				viewer.update(element, null);
				
			}
		});		
	}
	
	private void colcomboedit(TableViewer viewer, TableViewerColumn tableViewerColumnType) {
		ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer.getTable(),
				new String[] { "", GROUP_FIELD, GROUP_DATASET_SINGLE, GROUP_DATASET_MULTI });
		editor.setActivationStyle(ComboBoxCellEditor.DROP_DOWN_ON_TRAVERSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_KEY_ACTIVATION);

		tableViewerColumnType.setEditingSupport(new AbstractEditingSupport(viewer, editor) {

			@Override
			protected Object getValue(Object element) {
				return Integer.valueOf(0);
			}

			@Override
			protected void doSetValue(Object element, Object value) {
				System.out.println("dosetvalue setting value change : " + ((Integer) value).intValue());
				Person input = (Person) element;
				System.out.println(editor.getItems().toString());
				String[] s = editor.getItems();
//				input.setGender(String.valueOf(value));
				input.setGender(s[(int) value]);
				viewer.update(element, null);
			}
			
		});
	}
	
	private void colcomboeditorg(TableViewer viewer, TableViewerColumn tableViewerColumnType) {
		ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer.getTable(),
				new String[] { "", GROUP_FIELD, GROUP_DATASET_SINGLE, GROUP_DATASET_MULTI });
		editor.setActivationStyle(ComboBoxCellEditor.DROP_DOWN_ON_TRAVERSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_KEY_ACTIVATION);

		/*	CheckboxCellEditor editor = new CheckboxCellEditor(treeViewer_tobe.getTree());
			new String[] { "", GROUP_FIELD, GROUP_DATASET_SINGLE, GROUP_DATASET_MULTI });
	editor.setActivationStyle(ComboBoxCellEditor.DROP_DOWN_ON_TRAVERSE_ACTIVATION
			| ComboBoxCellEditor.DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION
			| ComboBoxCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION
			| ComboBoxCellEditor.DROP_DOWN_ON_KEY_ACTIVATION);*/

		tableViewerColumnType.setEditingSupport(new AbstractEditingSupport(viewer, editor) {

			@Override
			protected Object getValue(Object element) {

/*				if (((Element) element).hasAttribute(GROUP_ATTR_NAME)) {
					if (((Element) element).getAttribute(GROUP_ATTR_NAME).equals(GROUP_FIELD)) {
						return Integer.valueOf(1);
					} else if (((Element) element).getAttribute(GROUP_ATTR_NAME).equals(GROUP_DATASET_SINGLE)) {
						return Integer.valueOf(2);
					} else if (((Element) element).getAttribute(GROUP_ATTR_NAME).equals(GROUP_DATASET_MULTI)) {
						return Integer.valueOf(3);
					}
				}*/
				return Integer.valueOf(0);
			}

			@Override
			protected void doSetValue(Object element, Object value) {

//				System.out.println("dosetvalue setting value change : " + ((Integer) value).intValue());
				if (((Integer) value).intValue() == 0) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, "");
					viewer.update(element, null);
				} else if (((Integer) value).intValue() == 1) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_FIELD);
					viewer.update(element, null);
				} else if (((Integer) value).intValue() == 2) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_DATASET_SINGLE);
					viewer.update(element, null);
				} else if (((Integer) value).intValue() == 3) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_DATASET_MULTI);
					viewer.update(element, null);
				}
			}

		});
	}
	
	protected abstract class AbstractEditingSupport extends EditingSupport {
		private final CellEditor editor;

		public AbstractEditingSupport(TableViewer viewer) {
			super(viewer);
			this.editor = new TextCellEditor(viewer.getTable());
		}

		public AbstractEditingSupport(TableViewer viewer, CellEditor editor) {
			super(viewer);
			this.editor = editor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected void setValue(Object element, Object value) {
			doSetValue(element, value);
			getViewer().update(element, null);
		}

		protected abstract void doSetValue(Object element, Object value);
	}
	

}
