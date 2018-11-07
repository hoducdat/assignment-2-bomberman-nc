package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException, FileNotFoundException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
        try {
            String pathFileLevel = "res/levels/Level" + Integer.toString(level) + ".txt";
            Scanner reader = new Scanner(new File(pathFileLevel));
            _level = reader.nextInt();
            _height = reader.nextInt();
            _width = reader.nextInt();
            System.out.println(reader.nextLine().length());
            _map = new char[_height][_width];
            for (int i = 0; i < _height; i++) {
                _map[i] = reader.nextLine().substring(0, _width).toCharArray();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
			    switch (_map[y][x]) {
                    case '#':
                        addWall(x, y);
                        break;
                    case 'p':
                        addBomber(x, y);
                        break;
                    case '1':
                        addBalloon(x, y);
                        break;
                    case '*':
                        addBrick(x, y);
                        break;
                    case 'f':
                        addFlameIteam(x, y);
                        break;
                    case 'b':
                        addBombIteam(x, y);
                        break;
                    case 's':
                        addSpeedIteam(x, y);
                        break;
                    default:
                        addGrass(x, y);
                        break;
                }
			}
        }
	}

    private void addFlameIteam(int xI, int yI) {
        _board.addEntity(xI + yI * _width,
                new LayeredEntity(xI, yI,
                        new Grass(xI ,yI, Sprite.grass),
                        new FlameItem(xI, yI, Sprite.powerup_flames),
                        new Brick(xI, yI, Sprite.brick)
                )
        );
    }

    private void addSpeedIteam(int xI, int yI) {
        _board.addEntity(xI + yI * _width,
                new LayeredEntity(xI, yI,
                        new Grass(xI ,yI, Sprite.grass),
                        new FlameItem(xI, yI, Sprite.powerup_speed),
                        new Brick(xI, yI, Sprite.brick)
                )
        );
    }

    private void addBombIteam(int xI, int yI) {
        _board.addEntity(xI + yI * _width,
                new LayeredEntity(xI, yI,
                        new Grass(xI ,yI, Sprite.grass),
                        new FlameItem(xI, yI, Sprite.powerup_bombs),
                        new Brick(xI, yI, Sprite.brick)
                )
        );
    }

    private void addGrass(int x, int y) {
        int pos = x + y * _width;
        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
    }

    private void addWall(int x, int y) {
        int pos = x + y * _width;
        _board.addEntity(pos, new Wall(x, y, Sprite.wall));
    }

    private void addBomber(int xBomber, int yBomber) {
        _board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
        Screen.setOffset(0, 0);
        _board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
    }

    private void addBalloon(int xE, int yE) {
        _board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
        _board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
    }

    private void addBrick(int xB, int yB) {
        _board.addEntity(xB + yB * _width,
                new LayeredEntity(xB, yB,
                        new Grass(xB, yB, Sprite.grass),
                        new Brick(xB, yB, Sprite.brick)
                )
        );
    }

}
