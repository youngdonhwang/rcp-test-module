package com.hyd.rcp.test2;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.*;

import javax.inject.Inject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Element;
//import org.eclipse.ui.views.navigator.LocalSelectionTransfer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import com.hyd.ClassSet.Person;
//import com.lgcns.testpilot.rulemanager.tester.common.TPMSFileData;
//import com.lgcns.testpilot.rulemanager.recommender.MsgMappingRecom.AbstractEditingSupport;

import org.eclipse.swt.widgets.Text;
//import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
//import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swt.widgets.Label;


public class View extends ViewPart {
	public View() {
	}
	public static final String ID = "com.hyd.rcp.test2.view";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer_1, viewer_2;
	private TableViewerColumn column_1, column_2;
	public ArrayList<Person> p1= new ArrayList<Person>();
	public ArrayList<Person> p2= new ArrayList<Person>();
	public ArrayList<Person> p3= new ArrayList<Person>();
	private Table table_1, table_2;
	private Text text;
	private Text text_1;
	final static String GROUP_ATTR_NAME = "dataset"; // ? 원본 "gtype" 이였음. why
	final static String GROUP_FIELD = "field";
	final static String GROUP_DATASET_SINGLE = "dataset single";
	final static String GROUP_DATASET_MULTI = "dataset multi";
	
	private class StringLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		
		SashForm sashForm_2 = new SashForm(parent, SWT.NONE);
		SashForm sashForm = new SashForm(sashForm_2, SWT.BORDER | SWT.VERTICAL);
		
		init();

		SashForm sashForm_1 = new SashForm(sashForm_2, SWT.BORDER);
		
		viewer_1 = new TableViewer(sashForm_1, SWT.FULL_SELECTION | SWT.MULTI);
		table_1 = viewer_1.getTable();
		table_1.setHeaderVisible(true);
		viewer_1.getTable().setLinesVisible(true);
		
		viewer_2 = new TableViewer(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_2 = viewer_2.getTable();
		table_2.setHeaderVisible(true);
		viewer_2.getTable().setLinesVisible(true);
		
		
		
		Button btn1 = new Button(sashForm, SWT.NONE);
		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person out = (Person) viewer_1.getInput();
				System.out.println("result : " + out);
				viewer_1.refresh();
			}
		});
		btn1.setText("result");
		
		Button btn2 = new Button(sashForm, SWT.NONE);
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				p1.add(new Person("korea", "mycountry", "male", true));
			}
		});
		btn2.setText("setinput person");
		
		Button btnNewButton = new Button(sashForm, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer_1.add(new Person("새로운", "사람", "추가", true));
				viewer_1.applyEditorValue();
				viewer_1.update(e, null);
			}
		});
		btnNewButton.setText("Insert");
		
		Button btn3 = new Button(sashForm, SWT.NONE);
		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer_1.refresh();
				viewer_2.refresh();
			}
		});
		btn3.setText("refresh");
		
		Button btn4 = new Button(sashForm, SWT.NONE);
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				colview(viewer_1, column_1);
			}
		});
		btn4.setText("colview");
		
		Button btn5 = new Button(sashForm, SWT.NONE);
		btn5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer_1.update(e, null);
				
			}
		});
		btn5.setText("update");
		
		Button btn_prj = new Button(sashForm, SWT.NONE);
		btn_prj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IWorkspace ws = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root;
				IProject project;
				root = ws.getRoot();
				project = root.getProject("Pilottest");
//				project.exists();
				System.out.println("resource name : " + project.exists());
				final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
				System.out.println("prject name : " + projects.length);
								
				IPath p = new Path("/test/src/x.dioc");
				
				/*
				 * sample routine
				 */
				IWorkspaceRoot wsroot=ResourcesPlugin.getWorkspace().getRoot();

//				final IProject project = getProject1();
//				System.out.println("prject name : " + project.getName());
				
/*				
				 * swt origin routine
				 
//				String projectName = "SWTBot Java Project";
				String projectName = "com.hyd.rcp.test2";
//				bot.menu("File").menu("Project...").click();
//				SWTBotShell shell = bot.shell("New Project");
//				shell.activate();
//				bot.tree().expandNode("Java").select("Java Project");
//				bot.button("Next >").click();
//				bot.textWithLabel("Project name:").setText(projectName);
//				bot.button("Finish").click();
				final IProject project = getProject(projectName);
				if(project.exists()) System.out.println("project exist");
				else System.out.println("project no exist");
				
				final IFolder src = project.getFolder("src");
				final IFolder bin = project.getFolder("bin");
				
				 * hyd
				 
				System.out.println("src directory : " + src.toString());
				
				if (!src.exists()) {
					try {
						src.create(true, true, null);
					} catch (CoreException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				IFile test = src.getFile("Test.java");
				try {
					test.create(new ByteArrayInputStream("class Test{}".getBytes()), true,
							null);
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				project.getFolder("bin").getFile("Test.class").exists();
//				bot.waitUntil(new DefaultCondition() {
//					@Override
//					public boolean test() throws Exception {
//						return project.getFolder("bin").getFile("Test.class").exists();
//					}
//
//					public String getFailureMessage() {
//						return "File bin/Test.class was not created";
//					}
//				});
				if(bin.getFile("Test.class").exists()) System.out.println("test.class exist");
				else System.out.println("test.class no exist");
				
				final IProject project = getProject("com.hyd.rcp.test2");

				final IFolder src = project.getFolder("src");
				final IFolder bin = project.getFolder("bin");
				final IFolder hyd = project.getFolder("src");
				bin.getFullPath().toString();
				System.out.println("src dir : " + hyd.getFullPath().toString());
				System.out.println("src dir : " + hyd.getLocation());*/
			}
		});
		btn_prj.setText("Project Dir");
		
		Button btnPrintP = new Button(sashForm, SWT.NONE);
		btnPrintP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				viewer_2.getTable().
				p3 = (ArrayList<Person>) viewer_2.getInput();
				for(Person st : p2) {
					System.out.println("p2 value : " + st);
					
				}
				
				for(Person st : p3) {
					System.out.println("p3 value : " + st);
					
				}
			}
		});
		btnPrintP.setText("Print p2");
		
		text = new Text(sashForm, SWT.BORDER);
		
		DragSource dragSource_1 = new DragSource(text, DND.DROP_MOVE);
		
		text_1 = new Text(sashForm, SWT.BORDER);
		
		text_1.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent e) {
				text_1.setText("drag event");
			}
		});
		DropTarget dropTarget_1 = new DropTarget(text_1, DND.DROP_MOVE);
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setText("Drag&Drop test");
		
		
		/////////////////////////////////		
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
//		int operations = DND.DROP_COPY;
		DragSource source = new DragSource(lblNewLabel, operations);

		// Provide data in Text format
		Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
		source.setTransfer(types);
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		
		DropTarget dropTarget_2 = new DropTarget(lblNewLabel_1, operations);
		Transfer[] formats1 = new Transfer[] { TextTransfer.getInstance()};
		dropTarget_2.setTransfer(formats1);
		
		dropTarget_2.addDropListener(new DropTargetListener() {
			@Override
			public void dragEnter(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)){
				}
			}
			@Override
			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)){
					String files = (String)event.data;
					lblNewLabel_1.setText(files);
					
/*					for (int i = 0; i < files.length; i++) {
						lblNewLabel_1.setText(files);
//						text_filename.setText(files[i]);
					}*/
					System.out.println("drop event");
				}
			}
			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("dropleave event");
				
			}
			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drop");
				
			}
			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drop");
				
			}
			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				event.getSource();
				
			}
		});

		source.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				// Only start the drag if there is actually text in the
				// label - this text will be what is dropped on the target.
				if (lblNewLabel.getText().length() == 0) {
					event.doit = false;
				}
			}
			public void dragSetData(DragSourceEvent event) {
				// Provide the data of the requested type.
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = lblNewLabel.getText();
				}
			}
			public void dragFinished(DragSourceEvent event) {
				// If a move operation has been performed, remove the data
				// from the source
//				if (event.detail == DND.DROP_MOVE)
//					lblNewLabel.setText("");
			}
		});
		///////////////////////////
		
		dropTarget_1.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
		});
		dragSource_1.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragStart(DragSourceEvent event) {
			}
		});
		


//		column = new TableViewerColumn(viewer, SWT.NONE);
		
//		String[] colname = new String[] {"View"};
//		viewer.setColumnProperties(colname);
		
//		CellEditor[] celledit = new CellEditor[1]; 
//		TextCellEditor textCellEditor = new TextCellEditor(table);
//		celledit[0]	= textCellEditor;
//		viewer.setCellEditors(celledit);

		
//		viewer.getTable().getColumn(0).setWidth(200);
		viewer_1.setContentProvider(ArrayContentProvider.getInstance());
		viewer_1.setInput(p2);
		
		viewer_2.setContentProvider(ArrayContentProvider.getInstance());
		viewer_2.setInput(p1);
		
/*		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");*/
		
		/* 
		 * 컬럼 설정
		 */
//		column.setLabelProvider(new StringLabelProvider());
		colview(viewer_1, column_2);
		colview1(viewer_2, column_2);

		// Provide the input to the ContentProvider
//		viewer.setInput(createInitialDataModel());

		
		DragSource dragSource = new DragSource(table_1, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragStart(DragSourceEvent event) {
			}
			@Override
			public void dragSetData(DragSourceEvent event) {
				System.out.println("dragSetData()");
				IStructuredSelection selection = (IStructuredSelection) viewer_1.getSelection();
				LocalSelectionTransfer transfer = LocalSelectionTransfer.getTransfer();
				if (transfer.isSupportedType(event.dataType)) {
					transfer.setSelection(selection);
					transfer.setSelectionSetTime(event.time & 0xFFFF);
				}
			}
			@Override
			public void dragFinished(DragSourceEvent event) {
			}
		});
		
		int operations1 = DND.DROP_COPY| DND.DROP_MOVE;
		Transfer[] transferTypes = { LocalSelectionTransfer.getTransfer() };
		dragSource.setTransfer(transferTypes);
//		viewer.addDragSupport(operations, transferTypes , new MyDragListener(viewer));

		
		DropTarget dropTarget = new DropTarget(table_2, DND.DROP_MOVE);
		
		Transfer[] formats = { LocalSelectionTransfer.getTransfer() };;
		dropTarget.setTransfer(formats);
		
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
			@Override
			public void drop(DropTargetEvent event) {
				StructuredSelection selection = (StructuredSelection) event.data;
//				List<Person> elements = (List<Person>) new Person();
				List<Person> elements = selection.toList();
				System.out.println("drop event element " + elements);
				for(Object element : elements) {
					
				}
				viewer_2.add(elements.get(0));
				
			}
			@Override
			public void dropAccept(DropTargetEvent event) {
			}
			@Override
			public void dragOver(DropTargetEvent event) {
				event.getSource();
			}
		});
		
		sashForm.setWeights(new int[] {237, 221, 229, 229, 229, 229, 229, 229, 229, 229, 229, 229});
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm_2.setWeights(new int[] {111, 480});
		viewer_1.refresh();
		viewer_2.refresh();
		
		
	}


	@Override
	public void setFocus() {
		viewer_1.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("One", "Two", "Three", "Four");
	}
	
	private Person NewData() {

		return new Person("kim", "lee", "women", true);
	}
	
	public void colview(TableViewer viewer, TableViewerColumn column) {
		String[] titles = { "firstname", "lastname", "gender"};
		int[] bounds = {120,150,120}; 
		
//		Person p1 = new Person("hyd", "stm", "man", true);
		System.out.println("colview : " + viewer.getInput());

//		TableViewerColumn col; 
//		viewer.refresh();
		// 1st column is for msg grp id
		column = createTableViewerCol(viewer, titles[0], bounds[0], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getFirstName());
				return p.getFirstName();
			}
		});
		// 2st column is for msg grp id
		column = createTableViewerCol(viewer, titles[1], bounds[1], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getLastName());
				return p.getLastName();
			}
		});
		// 3st column is for msg grp id
		column = createTableViewerCol(viewer, titles[2], bounds[2], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getGender());
				return p.getGender();
			}
		});
		coledit(viewer, column);
	}
	
	public void colview1(TableViewer viewer, TableViewerColumn column) {
		String[] titles = { "성", "이름", "성별"};
		int[] bounds = {120,150,120}; 
		
//		Person p1 = new Person("hyd", "stm", "man", true);
		System.out.println("colview1 : " + viewer.getInput().toString());

//		TableViewerColumn col; 
//		viewer.refresh();
		// 1st column is for msg grp id
		column = createTableViewerCol(viewer, titles[0], bounds[0], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getFirstName());
				return p.getFirstName();
			}
		});
		// 2st column is for msg grp id
		column = createTableViewerCol(viewer, titles[1], bounds[1], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getLastName());
				return p.getLastName();
			}
		});
		// 3st column is for msg grp id
		column = createTableViewerCol(viewer, titles[2], bounds[2], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getGender());
				return p.getGender();
			}
			
		});
		colcomboedit(viewer, column);
	}
	
   
    private TableViewerColumn createTableViewerCol(TableViewer tableViewer, String title, int bound, final int colNumber) {
      final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
      final TableColumn column1 = viewerColumn.getColumn();
//    column.setAlignment(SWT.CENTER);
      
      column1.setText(title);
      column1.setWidth(bound);
      column1.setResizable(true);
      column1.setMoveable(true);
      
//    column.addSelectionListener(getSelectionAdapter(column, colNumber));
      
      return viewerColumn;
  }
    
    
	private IProject getProject(String name) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
	}

	private IProject getProject1() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject();
	}
	
	public void init() {
		// 데이터 초기화
		p1.add(new Person("hwang", "young", "male", true));
		p1.add(new Person("stmhyd", "lgcns", "male", true));
		p1.add(new Person("kim", "kang", "male", true));
		p1.add(new Person("sun", "dougther", "man", true));
		
		p2.add(new Person("hwang", "young", "male", true));
		p2.add(new Person("sun", "dougther", "man", true));
		p2.add(new Person("stmhyd", "lgcns", "male", true));
		p2.add(new Person("kim", "kang", "male", true));
	}
	
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
		ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer_2.getTable(),
				new String[] { "", GROUP_FIELD, GROUP_DATASET_SINGLE, GROUP_DATASET_MULTI });
		editor.setActivationStyle(ComboBoxCellEditor.DROP_DOWN_ON_TRAVERSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION
				| ComboBoxCellEditor.DROP_DOWN_ON_KEY_ACTIVATION);

		tableViewerColumnType.setEditingSupport(new AbstractEditingSupport(viewer_2, editor) {

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
				viewer_2.update(element, null);
			}
			
		});
	}
	
	private void colcomboeditorg(TableViewer viewer, TableViewerColumn tableViewerColumnType) {
		ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer_2.getTable(),
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

		tableViewerColumnType.setEditingSupport(new AbstractEditingSupport(viewer_2, editor) {

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
					viewer_2.update(element, null);
				} else if (((Integer) value).intValue() == 1) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_FIELD);
					viewer_2.update(element, null);
				} else if (((Integer) value).intValue() == 2) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_DATASET_SINGLE);
					viewer_2.update(element, null);
				} else if (((Integer) value).intValue() == 3) {
					((Element) element).setAttribute(GROUP_ATTR_NAME, GROUP_DATASET_MULTI);
					viewer_2.update(element, null);
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