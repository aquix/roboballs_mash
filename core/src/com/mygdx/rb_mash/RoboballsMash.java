package com.mygdx.rb_mash;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.screens.GameScreen;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.screens.MainMenuScreen;

public class RoboballsMash extends Game {
	private Game game = new Game() {
		@Override
		public void create() {
            AssetLoader.getInstance().load();
			setScreen(new MainMenuScreen(this));
		}

        @Override
        public void dispose() {
            super.dispose();
            AssetLoader.getInstance().dispose();
        }
	};

	public static Logger logger = new Logger("RoboballsMash");

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		logger.setLevel(Logger.DEBUG);

		game.create();
	}

	@Override
	public void render () {
		game.render();
	}
}
