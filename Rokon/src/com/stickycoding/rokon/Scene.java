package com.stickycoding.rokon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.microedition.khronos.opengles.GL10;

import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.stickycoding.rokon.device.Graphics;
import com.stickycoding.rokon.device.OS;

/**
 * Scene.java
 * A Scene holds and prepares drawable objects or object groups
 * 
 * @author Richard
 */
/**
 * @author Richard
 *
 */
public abstract class Scene {
	
	/**
	 * The maximum number of Runnables that can be queued at one time, shouldn't ever need to be more than a couple
	 */
	public static final int MAX_RUNNABLE = 24;
	
	/**
	 * The default number of layers if no number is passed
	 */
	public static final int DEFAULT_LAYER_COUNT = 1;
	
	/**
	 * The default number of objects per layer, if no number is passed 
	 */
	public static final int DEFAULT_LAYER_OBJECT_COUNT = 32;

	protected Layer[] layer;
	protected boolean loadedTextures;
	protected int layerCount;
	protected Window window = null;
	protected boolean useInvoke;
	protected World world;
	protected boolean usePhysics = false;
	protected ContactListener contactListener;
	protected boolean useContactListener;
	protected Background background;
	
	protected boolean useNewClearColor;
	protected float[] newClearColor = new float[4];
	
	protected float defaultLineWidth = 1;
	
	protected static Runnable[] uiRunnable = new Runnable[MAX_RUNNABLE];
	protected static Runnable[] gameRunnable = new Runnable[MAX_RUNNABLE];
	protected static long[] uiRunnableTime = new long[MAX_RUNNABLE];
	protected static long[] gameRunnableTime = new long[MAX_RUNNABLE];
	
	protected Texture[] forceTexture = new Texture[128];
	protected boolean hasForcedTexture = false;
	
	public RokonActivity activity;
	
	/**
	 * Forces a Texture to be loaded onto the hardware
	 * 
	 * @param texture
	 */
	public void forceTexture(Texture texture) {
		for(int i = 0; i < forceTexture.length; i++) {
			if(forceTexture[i] == null) {
				forceTexture[i] = texture;
				hasForcedTexture = true;
				break;
			}
		}
	}
	
	protected void checkForcedTextures() {
		if(hasForcedTexture) {
			for(int i = 0; i < forceTexture.length; i++) {
				if(forceTexture[i] != null) {
					GLHelper.checkTextureValid(forceTexture[i]);
					forceTexture[i] = null;
				}
			}
			hasForcedTexture = false;
		}
	}
	
	/**
	 * Queues a Runnable to be executed at the start of the next UI thread
	 * 
	 * @param runnable
	 */
	public boolean queueUI(Runnable runnable) {
		synchronized(RokonActivity.runnableLock) {
			for(int i = 0; i < MAX_RUNNABLE; i++) {
				if(Scene.uiRunnable[i] == null) {
					Scene.uiRunnable[i] = runnable;
					uiRunnableTime[i] = 0;
					return true;
				}
			}
			return false;
		}
	}
	
	protected void onUIRunnables() {
		synchronized(RokonActivity.runnableLock) {
			for(int i = 0; i < MAX_RUNNABLE; i++) {
				if(Scene.uiRunnable[i] != null && Time.getDrawTicks() > Scene.uiRunnableTime[i]) {
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putInt("index", i);
					message.setData(bundle);
					activity.executeRunnable.sendMessage(message);
				}
			}
		}
	}

	/**
	 * Queues a Runnable to be executed after a given delay
	 * 
	 * @param delay in milliseconds
	 * @param runnable
	 */
	public boolean queueUI(long delay, Runnable runnable) {
		synchronized(RokonActivity.runnableLock) {
			for(int i = 0; i < MAX_RUNNABLE; i++) {
				if(Scene.uiRunnable[i] == null) {
					Scene.uiRunnable[i] = runnable;
					uiRunnableTime[i] = delay + Time.getDrawTicks();;
					return true;
				}
			}
			return false;
		}
	}
	
	
	
	/**
	 * Queues a Runnable to be executed at the start of the next game loop
	 * 
	 * @param runnable
	 */
	public boolean queueGame(Runnable runnable) {
		synchronized(GameThread.runnableLock) {
			for(int i = 0; i < MAX_RUNNABLE; i++) {
				if(gameRunnable[i] == null) {
					gameRunnable[i] = runnable;
					gameRunnableTime[i] = 0;
					GameThread.hasRunnable = true;
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Queues a Runnable to be executed after a given delay
	 * 
	 * @param delay time, in milliseconds
	 * @param runnable
	 */
	public boolean queueGame(long delay, Runnable runnable) {
		synchronized(GameThread.runnableLock) {
			for(int i = 0; i < MAX_RUNNABLE; i++) {
				if(gameRunnable[i] == null) {
					gameRunnable[i] = runnable;
					gameRunnableTime[i] = delay + Time.getLoopTicks();
					GameThread.hasRunnable = true;
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Sets the clear colour (the colour behind the background)
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void setClearColour(float red, float green, float blue, float alpha) {
		newClearColor[0] = red;
		newClearColor[1] = green;
		newClearColor[2] = blue;
		newClearColor[3] = alpha;
		useNewClearColor = true;
	}

	/**
	 * Called before each render loop
	 * 
	 * @param gl GL10 object
	 */
	public void onPreDraw(GL10 gl) { }
	
	/**
	 * Called after each render loop
	 * @param gl GL10 object
	 */
	public void onPostDraw(GL10 gl) { }
	
	/**
	 * Called when the RokonActivity is hidden (usually with home button) 
	 */
	public abstract void onPause();
	
	/**
	 * Called when the RokonActivity is resumed  
	 */
	public abstract void onResume();
	
	/**
	 * Called at every loop
	 */
	public abstract void onGameLoop();
	
	public abstract void onReady();
	
	/**
	 * Triggered when a DrawableObjects .fade is completed
	 * 
	 * @param object the DrawableObject to which fade was applied
	 */
	public void onFadeEnd(DrawableObject object) { }
	
	/**
	 * Triggered when a DimensionalObject .move or .scale is completed
	 * 
	 * @param object the DimensionalObject to which .move was applied
	 */
	public void onMoveEnd(DimensionalObject object) { }
	
	/**
	 * Triggered when a DrawableObject's animate() is completed
	 * 
	 * @param drawableObject
	 */
	public void onAnimationEnd(DrawableObject drawableObject) { }
	
	public void onTouchDown(Drawable object, float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchUp(Drawable object, float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchMove(Drawable object, float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouch(Drawable object, float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchDown(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchMove(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouch(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchUp(float x, float y, int action, int pointerCount, int pointerId) { }
	
	public boolean onTouchCustom(float x[], float y[], int action, int pointerCount, int[] pointerId) { return false; }
	
	public void onTouchDownReal(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchMoveReal(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchUpReal(float x, float y, int action, int pointerCount, int pointerId) { }
	public void onTouchReal(float x, float y, int action, int pointerCount, int pointerId) { }

	public abstract void onKeyDown(int keyCode);
	public abstract void onKeyUp(int keyCode);
	public boolean onTrackballEvent(float x, float y, int action) { return false; }
	
	/**
	 * Adds a Drawable to the render queue at the current point. This should only be used in special cases.
	 * 
	 * @param drawable valid Drwaable
	 * @param useWindow TRUE if the Scenes Window should be used, FALSE if ignored
	 */
	public void render(Drawable drawable, boolean useWindow) {
		RokonActivity.renderQueueManager.add(drawable, useWindow);
	}
	
	public void render(Drawable drawable) {
		RokonActivity.renderQueueManager.add(drawable, true);
	}
	
	/**
	 * Sets a World for the physics in this Scene
	 * Automatically flags usePhysics
	 * 
	 * @param world valid World object
	 */
	public void setWorld(World world) {
		this.world = world;
		Physics.world = world;
		usePhysics = true;
	}
	
	/**
	 * Returns the World associated with this Scene
	 * 
	 * @return NULL if no World set
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Flags to use physics in this Scene
	 */
	public void usePhysics() {
		usePhysics = true;
	}
	
	/**
	 * Flags to not use physics
	 */
	public void noPhysics() {
		usePhysics = false;
	}
	
	/**
	 * Removes the World from this Scene
	 */
	public void removeWorld() {
		this.world = null;
		Physics.world = null;
		usePhysics = false;
	}
	
	/**
	 * Triggers the Scene to begin invoking methods on certain events, this is not set by default.
	 * If the methods that are to be invoked don't exist, no exceptions will be raised.
	 */
	public void useInvoke() {
		useInvoke = true;
	}
	
	/**
	 * Stops the Scene from invoking methods on events, this is the default state
	 */
	public void stopInvoke() {
		useInvoke = false;
	}
	
	/**
	 * Invokes a method inside the Scene class, defined by given parameters.
	 * If no parameters exist, use the alternative invoke method
	 * 
	 * @param methodName String
	 * @param params Class[]
	 * @param paramValues Object[]
	 * 
	 * @return TRUE if successful, FALSE otherwise
	 */
	public boolean invoke(String methodName, Class<?>[] params, Object[] paramValues) {
		for(Method m : this.getClass().getDeclaredMethods()) {
			if(m.getName().equals(methodName)) {
				if(Arrays.equals(params, m.getParameterTypes())) {
					try {
						m.invoke(this, paramValues);
						return true;
					} catch (IllegalArgumentException e) {
						Debug.error("Invoking, IllegalArgument");
						e.printStackTrace();
						return false;
					} catch (IllegalAccessException e) {
						Debug.error("Invoking, IllegalAccess");
						e.printStackTrace();
						return false;
					} catch (InvocationTargetException e) {
						Debug.error("Invoking, IllegalTarget");
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Invokes a method by parameters inside a Callback object
	 * 
	 * @param callback valid Callback object
	 * @return TRUE if successful, FALSE otherwise
	 */
	public boolean invoke(Callback callback) {
		if(callback.parameters == null) {
			return invoke(callback.methodName);
		} 
		if(callback.parameterTypes == null) {
			return invoke(callback.methodName, callback.parameters);
		}
		return invoke(callback.methodName, callback.parameterTypes, callback.parameters);
	}
	
	/**
	 * USE AT YOUR OWN RISK
	 * Invokes a method inside the Scene class, it selects the first matching method name and tries to pass on given parameters
	 * An error will be raised if this isn't the correct method. This routine is simply for those too lazy (or wanting to save
	 * on a little processing time) and are 100% sure there are no name conflicts.
	 * 
	 * IllegalArgumentException may be passed to the Debug class, logcat will be notified - but there is no way to test at your end.
	 * 
	 * @param methodName String
	 * @param paramValues Object[]
	 * 
	 * @return TRUE if successful, FALSE otherwise
	 */
	public boolean invoke(String methodName, Object[] paramValues) {
		for(Method m : this.getClass().getDeclaredMethods()) {
			if(m.getName().equals(methodName)) {
				try {
					m.invoke(this, paramValues);
					return true;
				} catch (IllegalArgumentException e) {
					Debug.error("Invoking, IllegalArgument");
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					Debug.error("Invoking, IllegalAccess");
					e.printStackTrace();
					return false;
				} catch (InvocationTargetException e) {
					Debug.error("Invoking, IllegalTarget");
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Invokes a method inside the Scene class, assuming there are no parameters to pass
	 * 
	 * @param methodName String
	 * 
	 * @return TRUE if successful, FALSE otherwise
	 */
	public boolean invoke(String methodName) {
		for(Method m : this.getClass().getDeclaredMethods()) {
			if(m.getName().equals(methodName)) {
				if(m.getParameterTypes().length == 0) {
					try {
						m.invoke(this);
						return true;
					} catch (IllegalArgumentException e) {
						Debug.error("Invoking, IllegalArgument");
						e.printStackTrace();
						return false;
					} catch (IllegalAccessException e) {
						Debug.error("Invoking, IllegalAccess");
						e.printStackTrace();
						return false;
					} catch (InvocationTargetException e) {
						Debug.error("Invoking, IllegalTarget");
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return false;
	}

	protected void handleSDK8MultiTouch(float[] x, float[] y, int action, int pointerCount, int[] pointerId) {
		for(int idx = 0; idx < pointerCount; idx++) {
			int id = pointerId[idx];
			final float _realX = x[idx];
			final float _realY = y[idx];
			final float realX = x[idx] * (RokonActivity.gameWidth / Graphics.getWidthPixels());
			final float realY = y[idx] * (RokonActivity.gameHeight / Graphics.getHeightPixels());
			float gameX = realX;
			float gameY = realY;
			if(window != null) {
				float xFraction = x[idx] / Graphics.getWidthPixels();
				float yFraction = y[idx] / Graphics.getHeightPixels();
				gameX = window.getX() + (window.width * xFraction);
				gameY = window.getY() + (window.height * yFraction);
			}
			for(int i = 0; i < layerCount; i++) {
				for(int j = 0; j < layer[i].maximumDrawableObjects; j++) {
					float checkX, checkY;
					checkX = gameX;
					checkY = gameY;
					if(layer[i].ignoreWindow) {
						checkX = realX;
						checkY = realY;
					}
					Drawable object = layer[i].gameObjects.get(j);
					if(object != null && object.isTouchable()) {
						boolean touched = false;
						if(object instanceof Sprite) {
							touched = MathHelper.pointInShape(checkX, checkY, (Sprite)object);
						} else {	
							touched = MathHelper.pointInRect(checkX, checkY, object.getX(), object.getY(), object.getWidth(), object.getHeight());
						}
						if(touched) {
							onTouch(object, checkX, checkY, action, pointerCount, id);
							if(object.getName() != null) {
								invoke(object.getName() + "_onTouch", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
							}
							switch(action & MotionEventWrapper8.ACTION_MASK) {
								case MotionEvent.ACTION_DOWN:
									onTouchDown(object, checkX, checkY, action, pointerCount, id);
									object.onTouchDown(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEvent.ACTION_UP:
									onTouchUp(object, checkX, checkY, action, pointerCount, id);
									object.onTouchUp(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEvent.ACTION_MOVE:
									onTouch(object, checkX, checkY, action, pointerCount, id);
									object.onTouchMove(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchMove", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEventWrapper8.ACTION_POINTER_DOWN:
									if((action & MotionEventWrapper8.ACTION_POINTER_INDEX_MASK) >> MotionEventWrapper8.ACTION_POINTER_INDEX_SHIFT == idx) {
										onTouchDown(object, checkX, checkY, action, pointerCount, id);
										object.onTouchDown(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper8.ACTION_POINTER_UP:
									if((action & MotionEventWrapper8.ACTION_POINTER_INDEX_MASK) >> MotionEventWrapper8.ACTION_POINTER_INDEX_SHIFT == idx) {
										onTouchUp(object, checkX, checkY, action, pointerCount, id);
										object.onTouchUp(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
							}
						}
					}
				}
			}
			onTouch(gameX, gameY, action, pointerCount, id);
			onTouchReal(_realX, _realY, action, pointerCount, id);
			switch(action & MotionEventWrapper8.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					onTouchDown(gameX, gameY, action, pointerCount, id);
					onTouchDownReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEvent.ACTION_UP:
					onTouchUp(gameX, gameY, action, pointerCount, id);
					onTouchUpReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEvent.ACTION_MOVE:
					onTouchMove(gameX, gameY, action, pointerCount, id);
					onTouchMoveReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEventWrapper8.ACTION_POINTER_DOWN:
					if((action & MotionEventWrapper8.ACTION_POINTER_INDEX_MASK) >> MotionEventWrapper8.ACTION_POINTER_INDEX_SHIFT == idx) {
						onTouchDown(gameX, gameY, action, pointerCount, id);
						onTouchDownReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper8.ACTION_POINTER_UP:
					if((action & MotionEventWrapper8.ACTION_POINTER_INDEX_MASK) >> MotionEventWrapper8.ACTION_POINTER_INDEX_SHIFT == idx) {
						onTouchUp(gameX, gameY, action, pointerCount, id);
						onTouchUpReal(_realX, _realY, action, pointerCount, id);
					}
					break;
			}
			
		}
	}
	
	protected void handleMultiTouch(float[] x, float[] y, int action, int pointerCount, int[] pointerId) {
		for(int idx = 0; idx < pointerCount; idx++) {
			int id = pointerId[idx];
			final float _realX = x[idx];
			final float _realY = y[idx];
			final float realX = x[idx] * (RokonActivity.gameWidth / Graphics.getWidthPixels());
			final float realY = y[idx] * (RokonActivity.gameHeight / Graphics.getHeightPixels());
			float gameX = realX;
			float gameY = realY;
			if(window != null) {
				float xFraction = x[idx] / Graphics.getWidthPixels();
				float yFraction = y[idx] / Graphics.getHeightPixels();
				gameX = window.getX() + (window.width * xFraction);
				gameY = window.getY() + (window.height * yFraction);
			}
			for(int i = 0; i < layerCount; i++) {
				for(int j = 0; j < layer[i].maximumDrawableObjects; j++) {
					float checkX, checkY;
					checkX = gameX;
					checkY = gameY;
					if(layer[i].ignoreWindow) {
						checkX = realX;
						checkY = realY;
					}
					Drawable object = layer[i].gameObjects.get(j);
					if(object != null && object.isTouchable()) {
						boolean touched = false;
						if(object instanceof Sprite) {
							touched = MathHelper.pointInShape(checkX, checkY, (Sprite)object);
						} else {	
							touched = MathHelper.pointInRect(checkX, checkY, object.getX(), object.getY(), object.getWidth(), object.getHeight());
						}
						if(touched) {
							onTouch(object, checkX, checkY, action, pointerCount, id);
							object.onTouch(checkX, checkY, action, pointerCount, id);
							if(object.getName() != null) {
								invoke(object.getName() + "_onTouch", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
							}
							switch(action) {
								case MotionEvent.ACTION_DOWN:
									onTouchDown(object, checkX, checkY, action, pointerCount, id);
									object.onTouchDown(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEvent.ACTION_UP:
									onTouchUp(object, checkX, checkY, action, pointerCount, id);
									object.onTouchUp(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEvent.ACTION_MOVE:
									onTouch(object, checkX, checkY, action, pointerCount, id);
									object.onTouchMove(checkX, checkY, action, pointerCount, id);
									if(object.getName() != null) {
										invoke(object.getName() + "_onTouchMove", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_1_DOWN:
									if(idx == 0) {
										onTouchDown(object, checkX, checkY, action, pointerCount, id);
										object.onTouchDown(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_1_UP:
									if(idx == 0) {
										onTouchUp(object, checkX, checkY, action, pointerCount, id);
										object.onTouchUp(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_2_DOWN:
									if(idx == 1) {
										onTouchDown(object, checkX, checkY, action, pointerCount, id);
										object.onTouchDown(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_2_UP:
									if(idx == 1) {
										onTouchUp(object, checkX, checkY, action, pointerCount, id);
										object.onTouchUp(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_3_DOWN:
									if(idx == 2) {
										onTouchDown(object, checkX, checkY, action, pointerCount, id);
										object.onTouchDown(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
								case MotionEventWrapper5.ACTION_POINTER_3_UP:
									if(idx == 2) {
										onTouchUp(object, checkX, checkY, action, pointerCount, id);
										object.onTouchUp(checkX, checkY, action, pointerCount, id);
										if(object.getName() != null) {
											invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, pointerCount, id });
										}
									}
									break;
							}
						}
					}
				}
			}
			
			onTouch(gameX, gameY, action, pointerCount, id);
			onTouchReal(_realX, _realY, action, pointerCount, id);
			switch(action) {
				case MotionEvent.ACTION_DOWN:
					onTouchDown(gameX, gameY, action, pointerCount, id);
					onTouchDownReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEvent.ACTION_UP:
					onTouchUp(gameX, gameY, action, pointerCount, id);
					onTouchUpReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEvent.ACTION_MOVE:
					onTouchMove(gameX, gameY, action, pointerCount, id);
					onTouchMoveReal(_realX, _realY, action, pointerCount, id);
					break;
				case MotionEventWrapper5.ACTION_POINTER_1_DOWN:
					if(idx == 0) {
						onTouchDown(gameX, gameY, action, pointerCount, id);
						onTouchDownReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper5.ACTION_POINTER_1_UP:
					if(idx == 0) {
						onTouchUp(gameX, gameY, action, pointerCount, id);
						onTouchUpReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper5.ACTION_POINTER_2_DOWN:
					if(idx == 1) {
						onTouchDown(gameX, gameY, action, pointerCount, id);
						onTouchDownReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper5.ACTION_POINTER_2_UP:
					if(idx == 1) {
						onTouchUp(gameX, gameY, action, pointerCount, id);
						onTouchUpReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper5.ACTION_POINTER_3_DOWN:
					if(idx == 2) {
						onTouchDown(gameX, gameY, action, pointerCount, id);
						onTouchDownReal(_realX, _realY, action, pointerCount, id);
					}
					break;
				case MotionEventWrapper5.ACTION_POINTER_3_UP:
					if(idx == 2) {
						onTouchUp(gameX, gameY, action, pointerCount, id);
						onTouchUpReal(_realX, _realY, action, pointerCount, id);
					}
					break;
			}
			
		}
	}

	
	protected void handleTouch(float[] x, float[] y, int action, int pointerCount, int[] pointerId) {
		if(onTouchCustom(x, y, action, pointerCount, pointerId)) return;
		if(OS.API_LEVEL >= 5) {
			if(OS.API_LEVEL >= 8) {
				handleSDK8MultiTouch(x, y, action, pointerCount, pointerId);
				return;
			}
			handleMultiTouch(x, y, action, pointerCount, pointerId);
			return;
		}
		final float realX = x[0] * (RokonActivity.gameWidth / Graphics.getWidthPixels());
		final float realY = y[0] * (RokonActivity.gameHeight / Graphics.getHeightPixels());
		float gameX = realX;
		float gameY = realY;
		if(window != null) {
			float xFraction = x[0] / Graphics.getWidthPixels();
			float yFraction = y[0] / Graphics.getHeightPixels();
			gameX = window.getX() + (window.width * xFraction);
			gameY = window.getY() + (window.height * yFraction);
		}

		for(int i = 0; i < layerCount; i++) {
			for(int j = 0; j < layer[i].maximumDrawableObjects; j++) {
				float checkX, checkY;
				checkX = gameX;
				checkY = gameY;
				if(layer[i].ignoreWindow) {
					checkX = realX;
					checkY = realY;
				}
				Drawable object = layer[i].gameObjects.get(j);
				if(object != null && object.isTouchable()) {
					boolean touched = false;
					if(object instanceof Sprite) {
						touched = MathHelper.pointInShape(checkX, checkY, (Sprite)object);
					} else {	
						touched = MathHelper.pointInRect(checkX, checkY, object.getX(), object.getY(), object.getWidth(), object.getHeight());
					}
					if(touched) {
						onTouch(object, checkX, checkY, action, 1, 0);
						if(object.getName() != null) {
							invoke(object.getName() + "_onTouch", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, 1, 0 });
						}
						object.onTouch(checkX, checkY, action, 1, 0);
						switch(action) {
							case MotionEvent.ACTION_DOWN:
								onTouchDown(object, checkX, checkY, action, 1, 0);
								object.onTouchDown(checkX, checkY, action, 1, 0);
								if(object.getName() != null) {
									invoke(object.getName() + "_onTouchDown", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, 1, 0 });
								}
								break;
							case MotionEvent.ACTION_UP:
								onTouchUp(object, checkX, checkY, action, 1, 0);
								object.onTouchUp(checkX, checkY, action, 1, 0);
								if(object.getName() != null) {
									invoke(object.getName() + "_onTouchUp", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, 1, 0 });
								}
								break;
							case MotionEvent.ACTION_MOVE:
								onTouch(object, checkX, checkY, action, 1, 0);
								object.onTouchMove(checkX, checkY, action, 1, 0);
								if(object.getName() != null) {
									invoke(object.getName() + "_onTouchMove", new Class[] { float.class, float.class, int.class, int.class, int.class }, new Object[] { gameX, gameY, action, 1, 0 });
								}
								break;
						}
					}
				}
			}
		}
		
		onTouch(gameX, gameY, action, 1, 0);
		onTouchReal(x[0], y[0], action, 1, 0);
		switch(action) {
			case MotionEvent.ACTION_DOWN:
				onTouchDown(gameX, gameY, action, 1, 0);
				onTouchDownReal(x[0], y[0], action, 1, 0);
				break;
			case MotionEvent.ACTION_UP:
				onTouchUp(gameX, gameY, action, 1, 0);
				onTouchUpReal(x[0], y[0], action, 1, 0);
				break;
			case MotionEvent.ACTION_MOVE:
				onTouchMove(gameX, gameY, action, 1, 0);
				onTouchMoveReal(x[0], y[0], action, 1, 0);
				break;
		}
	}
	
	/**
	 * Creates a new Scene with given layer count, and a corresponding maximum DrawableObject count 
	 * 
	 * @param layerCount maximum number of layers
	 * @param layerObjectCount maximum number of DrawableObjects per layer, the array length must match layerCount
	 */
	public Scene(int layerCount, int[] layerObjectCount) {
		this.layerCount = layerCount;
		layer = new Layer[layerCount];
		for(int i = 0; i < layerCount; i++) {
			layer[i] = new Layer(this, layerObjectCount[i]);
		}
	}
	
	/**
	 * Creates a new Scene with given layer count, all layers will have the same maximum number of DrawableObjects
	 * 
	 * @param layerCount maximum number of layers
	 * @param layerObjectCount maximum number of DrawableObjects per layer
	 */
	public Scene(int layerCount, int layerObjectCount) {
		this.layerCount = layerCount;
		layer = new Layer[layerCount];
		for(int i = 0; i < layerCount; i++) {
			layer[i] = new Layer(this, layerObjectCount);
		}
	}
	
	/**
	 * Creates a new Scene with given layer count, and a default maximum DrawableObject count of DEFAULT_LAYER_OBJECT_COUNT
	 * 
	 * @param layerCount maximum number of layers
	 */
	public Scene(int layerCount) {
		this(layerCount, DEFAULT_LAYER_OBJECT_COUNT);
	}
	
	/**
	 * Creates a new Scene with defaults, DEFAULT_LAYER_COUNT and DEFAULT_LAYER_OBJECT_COUNT
	 */
	public Scene() {
		this(DEFAULT_LAYER_COUNT, DEFAULT_LAYER_OBJECT_COUNT);
	}
	
	/**
	 * Fetches the current Activity
	 * 
	 * @return the current RokonActivity
	 */
	public RokonActivity getActivity() {
		return activity;
	}
	
	/**
	 * Defines the active Window for this Scene
	 * If no Window is given, a default static view will be rendered 
	 * 
	 * @param window
	 */
	public void setWindow(Window window) {
		if(window == null) {
			Debug.warning("Scene.setWindow", "Tried setting a NULL Window");
			return;
		}
		this.window = window;
	}
	
	/**
	 * Removes the current active Window, returning it to NULL
	 */
	public void removeWindow() {
		window = null;
	}
	
	/**
	 * @return NULL if there is no Window associated with this Scene
	 */
	public Window getWindow() {
		if(window == null)
			return null;
		return window;
	}
	
	/**
	 * Fetches the Layer object associated with the given index
	 * 
	 * @param index the index of the Layer
	 * @return NULL if invalid index is given
	 */
	public Layer getLayer(int index) {
		if(index < 0 || index > layerCount) {
			Debug.warning("Scene.getLayer", "Tried fetching invalid layer (" + index + "), maximum is " + layerCount);
			return null;
		}
		return layer[index];
	}
	
	/**
	 * Clears the DrawableObjects from all Layers
	 */
	public void clear() {
		for(int i = 0; i < layerCount; i++) {
			layer[i].clear();
		}
	}
	
	/**
	 * Clears all the DrawableObjects from a specified Layer
	 * 
	 * @param index the index of the Layer
	 */
	public void clearLayer(int index) {
		if(index <= 0 || index > layerCount) {
			Debug.warning("Scene.clearLayer", "Tried clearing invalid layer (" + index + "), maximum is " + layerCount);
			return;
		}
		layer[index].clear();
	}
	
	/**
	 * Moves a Layer from one index to another, and shuffles the others up or down to accomodate
	 * 
	 * @param startIndex the current index of the Layer
	 * @param endIndex the desired final index of the Layer
	 */
	public void moveLayer(int startIndex, int endIndex) {
		if(startIndex == endIndex) {
			Debug.warning("Scene.moveLayer", "Tried moving a Layer to its own position, stupid");
			return;
		}
		if(startIndex <= 0 || startIndex > layerCount) {
			Debug.warning("Scene.moveLayer", "Tried moving an invalid Layer, startIndex=" + startIndex + ", maximum is " + layerCount);
			return;
		}
		if(endIndex <= 0 || endIndex > layerCount) {
			Debug.warning("Scene.moveLayer", "Tried moving an invalid Layer, endIndex=" + endIndex + ", maximum is " + layerCount);
			return;
		}
		Layer temporaryLayer = layer[startIndex];
		if(endIndex < startIndex) {
			for(int i = endIndex; i < startIndex; i++) {
				layer[i + 1] = layer[i];
			}
			layer[endIndex] = temporaryLayer;
		}
		if(endIndex > startIndex) { 
			for(int i = startIndex; i < endIndex; i++) {
				layer[i] = layer[i + 1];
			}
			layer[endIndex] = temporaryLayer;
		}
	}
	
	/**
	 * Switches the position of one Layer with another
	 * 
	 * @param layer1 the index of the first Layer
	 * @param layer2 the index of the second Layer
	 */
	public void switchLayers(int layer1, int layer2) {
		if(layer1 == layer2) {
			Debug.warning("Scene.switchLayers", "Tried switching the same Layer");
			return;
		}
		if(layer1 < 0 || layer1 > layerCount) {
			Debug.warning("Scene.switchLayers", "Tried switch an invalid Layer, layer1=" + layer1 + ", maximum is " + layerCount);
			return;
		}
		if(layer2 < 0 || layer2 > layerCount) {
			Debug.warning("Scene.switchLayers", "Tried switch an invalid Layer, layer2=" + layer2 + ", maximum is " + layerCount);
			return;
		}
		Layer temporaryLayer = layer[layer1];
		layer[layer1] = layer[layer2];
		layer[layer2] = temporaryLayer;
	}
	
	/**
	 * Replaces a Layer object in this Scene
	 * 
	 * @param index a valid index for a Layer, less than getLayerCount
	 * @param layer a valid Layer object to replace the existing Layer
	 */
	public void setLayer(int index, Layer layer) {
		if(layer == null) {
			Debug.warning("Scene.setLayer", "Tried setting to a null Layer");
			return;
		}
		if(index < 0 || index > layerCount) {
			Debug.warning("Scene.setLayer", "Tried setting an invalid Layer, index=" + index + ", maximum is " + layerCount);
			return;
		}
		this.layer[index] = layer;
	}
	
	/**
	 * Adds a DrawableObject to the first (0th) Layer
	 * 
	 * @param drawableObject a valid DrawableObject
	 */
	public void add(GameObject drawableObject) {
		layer[0].add(drawableObject);
	}
	
	/**
	 * Adds a DrawableObject to a given Layer
	 * 
	 * @param layerIndex a valid index of a Layer
	 * @param drawableObject a valid DrawableObject
	 */
	public void add(int layerIndex, GameObject drawableObject) {
		if(layerIndex < 0 || layerIndex > layerCount) {
			Debug.warning("Scene.add", "Tried adding to an invalid Layer, layerIndex=" + layerIndex + ", maximum is " + layerCount);
			return;
		}
		if(drawableObject == null) {
			Debug.warning("Scene.add", "Tried adding a NULL DrawableObject");
			return;
		}
		layer[layerIndex].add(drawableObject);
	}
	
	/**
	 * Removes a DrawableObject from the Scene
	 * 
	 * @param drawableObject a valid DrawableObject
	 */
	public void remove(DrawableObject drawableObject) {
		drawableObject.remove();
	}
	
	protected void onSetScene() {
		loadedTextures = false;
		activity = Rokon.currentActivity;
	}
	
	protected void onEndScene() {

	}
	
	protected void render() {
		try {
			for(int i = 0; i < layerCount; i++) {
				layer[i].render();
			}
		} catch (Exception e) { 
			Debug.error("ERROR IN SCENE RENDER");
			e.printStackTrace();
		}
	}
	
	protected boolean pausePhysics = false;
	
	/**
	 * Pause the Box2D physics, but continue drawing
	 */
	public void pausePhysics() {
		pausePhysics = true;
	}
	
	/**
	 * Resumes the Box2D physics
	 */
	public void resumePhysics() {
		pausePhysics = false;
	}
	
	/**
	 * Fetches the current state of the physics engine
	 * 
	 * @return TRUE if physics is paused, FALSE otherwise
	 */
	public boolean isPhysicsPaused() {
		return pausePhysics;
	}
	
	/**
	 * Sets the Background for this Scene
	 * 
	 * @param background valid Background object
	 */
	public void setBackground(Background background) {
		this.background = background;
		background.parentScene = this;
	}
	
	/**
	 * Gets the current Background of the Scene
	 * 
	 * @return NULL if none set
	 */
	public Background getBackground() {
		return this.background;
	}
	
	/**
	 * Sets the default line width for this Scene, by default, 1f
	 * 
	 * @param lineWidth float > 0f
	 */
	public void setDefaultLineWidth(float lineWidth) {
		defaultLineWidth = lineWidth;
	}

	/**
	 * Gets the current default line width in this Scene
	 * 
	 * @return 1f by default
	 */
	public float getDefaultLineWidth() {
		return defaultLineWidth;
	}
	
	/**
	 * Shows toast for a short period of time
	 * 
	 * @param message message string
	 */
	public static void toastShort(String message) {
		RokonActivity.toastMessage = message;
		RokonActivity.toastType = Toast.LENGTH_SHORT;
		RokonActivity.toastHandler.sendEmptyMessage(0);
	}
	
	/**
	 * Shows toast for a longer period of time
	 * 
	 * @param message message string
	 */
	public static void toastLong(String message) {
		RokonActivity.toastMessage = message;
		RokonActivity.toastType = Toast.LENGTH_LONG;
		RokonActivity.toastHandler.sendEmptyMessage(0);
	}
	
	public float getRealX(float gameX) {
		if(window != null) {
			return ((gameX - window.getX()) / (float)window.width) * Graphics.getWidthPixels();
		} else {
			return gameX / ((float)RokonActivity.gameWidth / Graphics.getWidthPixels());
		}
	}
	
	public float getRealY(float gameY) {
		if(window != null) {
			return ((gameY - window.getY()) / (float)window.height) * Graphics.getHeightPixels();
		} else {
			return gameY / ((float)RokonActivity.gameHeight / Graphics.getHeightPixels());
		}
	}

	
	/**
	 * Fetches the screen X coordinate of a position in game-space
	 * 
	 * @param gameX X coordinate in game-space
	 * @param ignoreWindow TRUE if the Window (if there is one) should be ignored
	 * @return X coordinate on device screen (in pixels)
	 */
	public float getRealX(float gameX, boolean ignoreWindow) {
		if(window != null) {
			return ((gameX - window.getX()) / (float)window.width) * Graphics.getWidthPixels();
		} else {
			return gameX / ((float)RokonActivity.gameWidth / Graphics.getWidthPixels());
		}
	}
	
	/**
	 * Fetches the screen Y coordinate of a position in game-space
	 * 
	 * @param gameY Y coordinate in game-space
	 * @param ignoreWindow TRUE if the Window (if there is one) should be ignored
	 * @return Y coordinate on device screen (in pixels)
	 */
	public float getRealY(float gameY, boolean ignoreWindow) {
		if(window != null) {
			return ((gameY - window.getY()) / (float)window.height) * Graphics.getHeightPixels();
		} else {
			return gameY / ((float)RokonActivity.gameHeight / Graphics.getHeightPixels());
		}
	}
	
	
}
