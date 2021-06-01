package com.ray3k.template.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;

import java.util.Arrays;

import static com.ray3k.template.Core.*;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher implements CrossPlatformWorker {
	public static void main(String[] args) {
		createApplication();
	}

	private static Lwjgl3Application createApplication() {
		crossPlatformWorker = new Lwjgl3Launcher();
		return new Lwjgl3Application(new Core(), getDefaultConfiguration());
	}

	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
		var config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("ray3k-jam-template");
		config.setWindowedMode(1024, 576);
		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 3);
		config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
		return config;
	}
	
	@Override
	public Table generateDebugTable() {
		var table = new Table();
		table.defaults().space(5);
		var iter = Arrays.stream(Values.class.getFields()).iterator();
		while (iter.hasNext()) {
			var field = iter.next();
			try {
				if (field.getType() == Integer.TYPE) {
					var label = new Label(field.getName() + ":", skin);
					table.add(label).right();
					
					var textField = new TextField(Integer.toString(field.getInt(null)), skin);
					textField.setTextFieldFilter((textField1, c) -> Character.isDigit(c) || c == '-');
					table.add(textField).expandX().left();
					textField.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							try {
								var text = textField.getText();
								field.set(null, text == null ? 0 : Integer.parseInt(text));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				} else if (field.getType() == Boolean.TYPE) {
					var textButton = new TextButton(field.getName(), skin, "toggle");
					textButton.setChecked(field.getBoolean(null));
					table.add(textButton).colspan(2);
					
					textButton.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							try {
								field.set(null, textButton.isChecked());
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				} else if (field.getType() == String.class) {
					var label = new Label(field.getName() + ":", skin);
					table.add(label).right();
					
					var textField = new TextField((String)field.get(null), skin);
					table.add(textField).expandX().left();
					
					textField.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							try {
								field.set(null, textField.getText());
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				} else if (field.getType() == Float.TYPE) {
					var label = new Label(field.getName() + ":", skin);
					table.add(label).right();
					
					var textField = new TextField(Float.toString(field.getFloat(null)), skin);
					textField.setTextFieldFilter((textField1, c) -> Character.isDigit(c) || c == '-' || c == '.');
					table.add(textField).expandX().left();
					textField.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							try {
								var text = textField.getText();
								field.set(null, text == null ? 0 : Float.parseFloat(text));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				} else if (field.getType() == Range.class) {
					var label = new Label(field.getName() + ":", skin);
					table.add(label).right();
					
					var subTable = new Table();
					table.add(subTable).left();
					
					var range = (Range) field.get(null);
					var slider = new Slider(range.min,  range.max, .01f, false, skin);
					var fieldValue = iter.next();
					slider.setValue(fieldValue.getFloat(null));
					subTable.add(slider);
					
					var valueLabel = new Label(Float.toString(slider.getValue()), skin);
					subTable.add(valueLabel).minWidth(100f).space(5f);
					
					slider.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							try {
								fieldValue.setFloat(null, slider.getValue());
								valueLabel.setText(String.format("%.2f", slider.getValue()));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			table.row();
		}
		
		return table;
	}
}