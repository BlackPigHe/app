package com.example.gameContent;




import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;

public class GameActivity extends RokonActivity {
	public static final float GAME_WIDTH=480f;
	public static final float GAME_HEIGHT=320f;
	private GameScene mGameScene;
	public static float scaleFactor = 0.0f;
	public void onCreate(){
		debugMode();
    	forceFullscreen();
    	forceLandscape();//强制横向显示
    	
    	setGameSize(GAME_WIDTH,GAME_HEIGHT);
    	setDrawPriority(DrawPriority.VBO);//设置画布优先的模式"骨头按"
    	setGraphicsPath("textures/");//设置照片的存放路径
    	createEngine();//创建引擎
    	System.out.println("引擎创建完毕");
	}
	public void onLoadComplete(){
		Textures.onLoad();
		System.out.println("纹理加载完毕");
    	//Sounds.load();
		createScene();
    	
    	//CreateScenes();
    	
	}
	private void createScene() {
		// TODO Auto-generated method stub
		setScene(new GameScene());
		System.out.println("场景实例化完毕");
	}
	

}
