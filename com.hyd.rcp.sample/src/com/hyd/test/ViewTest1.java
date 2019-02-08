package com.hyd.test;

//import junit.framework.Test;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import com.hyd.rcp.test2.View;

public class ViewTest1 {

	@Test
	public void testView() {
		Display display = new Display();
		Shell shell = new Shell(display);
		ViewSample view = new ViewSample();
		
		view.createPartControl(shell);
		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		
		
	}
}
