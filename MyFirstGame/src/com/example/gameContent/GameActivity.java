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
    	forceLandscape();//ǿ�ƺ�����ʾ
    	
    	setGameSize(GAME_WIDTH,GAME_HEIGHT);
    	setDrawPriority(DrawPriority.VBO);//���û������ȵ�ģʽ"��ͷ��"
    	setGraphicsPath("textures/");//������Ƭ�Ĵ��·��
    	createEngine();//��������
    	System.out.println("���洴�����");
	}
	public void onLoadComplete(){
		Textures.onLoad();
		System.out.println("����������");
    	//Sounds.load();
		createScene();
    	
    	//CreateScenes();
    	
	}
	private void createScene() {
		// TODO Auto-generated method stub
		setScene(new GameScene());
		System.out.println("����ʵ�������");
	}
	

}
