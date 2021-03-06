package com.gs.dbex.common.business
{
	import com.gs.dbex.DbexCommonModelLocator;
	import com.gs.dbex.application.view.DbexApplicationViewHelper;
	import com.gs.dbex.common.view.ViewLocator;
	import com.gs.dbex.vo.UserVO;
	
	import mx.controls.Alert;
	import mx.messaging.messages.ErrorMessage;
	import mx.utils.ObjectUtil;
	
	public class DbexUserResponder implements BaseResponder
	{
		[Bindable]
		private var dbexCommonModelLocator:DbexCommonModelLocator = DbexCommonModelLocator.getInstance();
		
		public function DbexUserResponder()
		{
			super();
		}

		public function onResult(event:*=null):void
		{
			if(ViewLocator.getInstance().registrationExistsFor("DbexApplicationViewHelper")){
				var viewHelper:DbexApplicationViewHelper = ViewLocator.getInstance().getViewHelper("DbexApplicationViewHelper") as DbexApplicationViewHelper;
				if(null != viewHelper){
					var userVO:UserVO = UserVO(event.result);
					dbexCommonModelLocator.loggedInUserVO = userVO;
					dbexCommonModelLocator.connectionPropertiesColl = userVO.connectionPropertiesVOs;
					trace(ObjectUtil.toString(userVO));
					viewHelper.handelLoginSuccess(userVO);
				}
			}	
		}
		
		
		public function onFault(event:*=null):void
		{
			/* var faultEvt:FaultEvent = event as FaultEvent;
			if(null != faultEvt){
				
			} */
			var errorMessage:ErrorMessage = event.message as ErrorMessage;

			Alert.show("Some error occured !!!\n\n"
				+ errorMessage.rootCause.exceptionMessage, "Error");
			if(ViewLocator.getInstance().registrationExistsFor("DbexApplicationViewHelper")){
				var viewHelper:DbexApplicationViewHelper = ViewLocator.getInstance().getViewHelper("DbexApplicationViewHelper") as DbexApplicationViewHelper;
				if(null != viewHelper){
					viewHelper.moveToLoginScreen();
				}
			}
		}
		
	}
}