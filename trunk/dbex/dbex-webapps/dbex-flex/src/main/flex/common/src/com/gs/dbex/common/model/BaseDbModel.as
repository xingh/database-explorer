package com.gs.dbex.common.model
{
	import com.gs.dbex.IDbexObject;

	public class BaseDbModel implements IDbexObject
	{
		
		public var modelName:String;
		public var comments:String;
		public var deleted:Boolean = false;
		public var modelType:String;
		public var schemaName:String;
		public var createTime:Date;
		public var updateTime:Date;
		
		public function BaseDbModel()
		{
		}

		public function toString():String
		{
			return modelName;
		}
		
		public function equals(obj:Object):Boolean
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj is BaseDbModel))
				return false;
			var other:BaseDbModel = obj as BaseDbModel;
			if (modelName == null) {
				if (other.modelName != null)
					return false;
			} else if (!modelName.equals(other.modelName))
				return false;
			return true;
		}
		
	}
}