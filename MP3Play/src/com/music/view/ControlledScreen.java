package com.music.view;

import com.music.view.controller.StageController;

public interface ControlledScreen {
	//This method will allow the injection of the Parent ScreenPane
	public void setScreenParent(StageController screenpage);
}
