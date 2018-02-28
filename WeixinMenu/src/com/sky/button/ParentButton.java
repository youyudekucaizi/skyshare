package com.sky.button;

public class ParentButton extends Button
{
	private Button[] sub_button;//这里的sub_Button可以是commonbutton
	//Button button=new CommonButton();

	public Button[] getSub_button()
	{
		return sub_button;
	}

	public void setSub_button(Button[] sub_button)
	{
		this.sub_button = sub_button;
	}
	
//	public Button[] getSub_Button()
//	{
//		return sub_Button;
//	}
//
//	public void setSub_Button(Button[] sub_Button)
//	{
//		this.sub_Button = sub_Button;
//	}
//	

}
