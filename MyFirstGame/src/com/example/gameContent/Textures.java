package com.example.gameContent;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

//����Դ���ص�����
public class Textures {
	public static TextureAtlas atlas;//�����ͼ
	public static Texture background,peach,gailun,nuoshou,yasuo,huonan;
	
	
	public static Texture getGailun() {
		return gailun;
	}


	public static void setGailun(Texture gailun) {
		Textures.gailun = gailun;
	}


	public static Texture getNuoshou() {
		return nuoshou;
	}


	public static void setNuoshou(Texture nuoshou) {
		Textures.nuoshou = nuoshou;
	}


	public static Texture getYasuo() {
		return yasuo;
	}


	public static void setYasuo(Texture yasuo) {
		Textures.yasuo = yasuo;
	}


	public static Texture getHuonan() {
		return huonan;
	}


	public static void setHuonan(Texture huonan) {
		Textures.huonan = huonan;
	}


	public static void onLoad(){
		atlas =new TextureAtlas();
		background=new Texture("background.png");
		atlas.insert(background);
		atlas.insert(peach=new Texture("peach.png"));
		atlas.insert(gailun=new Texture("gailun.png"));
		atlas.insert(nuoshou=new Texture("nuoshou.png"));
		atlas.insert(yasuo=new Texture("yasuo.png"));
		atlas.insert(huonan=new Texture("huonan.png"));
		//���մ�С˳�������ӣ������ǰС���ں�
		atlas.complete();
	}

}
