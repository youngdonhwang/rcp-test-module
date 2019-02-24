package com.hyd.util;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.hyd.ClassSet.Person;

public class ViewerSortComparator extends ViewerComparator  {

	private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public ViewerSortComparator() {
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        Person p1 = (Person) e1;
        Person p2 = (Person) e2;
        int rc = 0;
        
        switch (propertyIndex) {

        case 0:
            rc = p1.getFirstName().compareTo(p2.getFirstName());
            System.out.println("firstname sort");
            break;
        case 1:
            rc = p1.getLastName().compareTo(p2.getLastName());
            System.out.println("lasttname sort");
            break;

        default:
            rc = 0;
            System.out.println("nothing sort");
            
        }
        // If descending order, flip the direction
        if (direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }
    
	public static void sortTable(Table table, int columnIndex) {
		if(table == null || table.getColumnCount() <= 1)
			return;
		if(columnIndex < 0 || columnIndex >= table.getColumnCount())
			throw new IllegalArgumentException("The specified column does not exist. ");
		
		final int colIndex = columnIndex;
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((TableItem)o1).getText(colIndex).compareTo(((TableItem)o2).getText(colIndex));
			}

			public boolean equals(Object obj) {
				return false;
			}
		};
		
		TableItem[] tableItems = table.getItems();
		Arrays.sort(tableItems, comparator);
		
		for(int i=0; i<tableItems.length; i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			for(int j=0; j<table.getColumnCount(); j++) {
				item.setText(j, tableItems[i].getText(j));
				item.setImage(j, tableItems[i].getImage(j));
			}
			tableItems[i].dispose();
		}
	}

}
