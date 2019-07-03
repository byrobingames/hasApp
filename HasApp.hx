package;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#else
import openfl.Lib;
#end

#if android
#if openfl_legacy
import openfl.utils.JNI;
#else
import lime.system.JNI;
#end
#end

class HasApp
{
    #if android
    private static var ANDROID_CLASS:String = "com/byrobin/hasApp/HasApp";

	private static var __checkpackage_android:Dynamic;
	private static var __checkappname_android:Dynamic;		
	public static var _package:Array<Dynamic>;
	public static var _appnames:Array<Dynamic>;
	
	private function new() {
	
		_package =[];
		_appnames =[];
	
	}
	
	public static function checkPackage() {	
        	try {		
				trace("CheckPackage");			
       	     	__checkpackage_android = JNI.createStaticMethod(ANDROID_CLASS, "checkPackage", "(Lorg/haxe/lime/HaxeObject;)V", true);

       	    	var args = new Array<Dynamic>();
				args.push(new HasApp());
            	__checkpackage_android(args);
			} catch (e:Dynamic) {
       			trace("Could not detect if an package is installed");
        	}
		
    }
	
	public static function getInstalledPackage():Array<Dynamic> {
        
        return _package;
    }
	
	public static function checkAppName() {	
        	try {		
				trace("CheckAppname");			
       	     	__checkappname_android = JNI.createStaticMethod(ANDROID_CLASS, "checkAppName", "(Lorg/haxe/lime/HaxeObject;)V", true);

       	    	var args = new Array<Dynamic>();
				args.push(new HasApp());
            	__checkappname_android(args);
			} catch (e:Dynamic) {
       			trace("Could detect if an package is installed");
        	}
		
    }
	
	public static function getInstalledAppname():Array<Dynamic> {
        
        return _appnames;
    }
	
	
	/////////////Android Event	
	
	public function isPackageInstalled(packages:String = "") {
		
		var p = packages.split(",");
		_package = p;
		
		//trace("Package " + _package);
    }
	
	public function isAppInstalled(appnames:String) {
	
		var a = appnames.split(",");
		_appnames = a;
		
		//trace("AppName: " + _appnames);
    }
		
#end


}
