package kdg.be.parchis.model.game;

import java.util.HashMap;
import java.util.Map;

public class CoordinateConverter {
    private Map<Integer, Position> positionMap = new HashMap<>();

    public CoordinateConverter() {
        //NESTS
        //yellow nest
        positionMap.put(6901, new Position(160, -150));
        positionMap.put(6902, new Position(185, -150));
        positionMap.put(6903, new Position(210, -150));
        positionMap.put(6904, new Position(235, -150));
        //blue nest
        positionMap.put(7001, new Position(-160, -150));
        positionMap.put(7002, new Position(-185, -150));
        positionMap.put(7003, new Position(-210, -150));
        positionMap.put(7004, new Position(-235, -150));
        //red nest
        positionMap.put(7101, new Position(-160, 180));
        positionMap.put(7102, new Position(-185, 180));
        positionMap.put(7103, new Position(-210, 180));
        positionMap.put(7104, new Position(-235, 180));
        //green nest
        positionMap.put(7201, new Position(160, 180));
        positionMap.put(7202, new Position(185, 180));
        positionMap.put(7203, new Position(210, 180));
        positionMap.put(7204, new Position(235, 180));

        //yellow start position
        positionMap.put(5, new Position(170, -40));

        //blue start position
        positionMap.put(22, new Position(-50, -160));

        //red start position
        positionMap.put(39, new Position(-173, 60));

        //green start position
        positionMap.put(56, new Position(45, 185));





        /*
        // Positions on board.
        positionMap.put(1, new Position());
        positionMap.put(2, new Position());
        positionMap.put(3, new Position());
        positionMap.put(4, new Position());
        positionMap.put(6, new Position());
        positionMap.put(7, new Position());
        positionMap.put(8, new Position());
        positionMap.put(9, new Position());
        positionMap.put(10, new Position());
        positionMap.put(11, new Position());
        positionMap.put(12, new Position());
        positionMap.put(13, new Position());
        positionMap.put(14, new Position());
        positionMap.put(15, new Position());
        positionMap.put(16, new Position());
        positionMap.put(17, new Position());
        positionMap.put(18, new Position());
        positionMap.put(19, new Position());
        positionMap.put(20, new Position());
        positionMap.put(21, new Position());
        positionMap.put(23, new Position());
        positionMap.put(24, new Position());
        positionMap.put(25, new Position());
        positionMap.put(26, new Position());
        positionMap.put(27, new Position());
        positionMap.put(28, new Position());
        positionMap.put(29, new Position());
        positionMap.put(30, new Position());
        positionMap.put(31, new Position());
        positionMap.put(32, new Position());
        positionMap.put(33, new Position());
        positionMap.put(34, new Position());
        positionMap.put(35, new Position());
        positionMap.put(36, new Position());
        positionMap.put(38, new Position());
        positionMap.put(40, new Position());
        positionMap.put(41, new Position());
        positionMap.put(42, new Position());
        positionMap.put(43, new Position());
        positionMap.put(44, new Position());
        positionMap.put(45, new Position());
        positionMap.put(46, new Position());
        positionMap.put(47, new Position());
        positionMap.put(48, new Position());
        positionMap.put(49, new Position());
        positionMap.put(50, new Position());
        positionMap.put(51, new Position());
        positionMap.put(52, new Position());
        positionMap.put(53, new Position());
        positionMap.put(54, new Position());
        positionMap.put(55, new Position());
        positionMap.put(57, new Position());
        positionMap.put(58, new Position());
        positionMap.put(59, new Position());
        positionMap.put(60, new Position());
        positionMap.put(61, new Position());
        positionMap.put(62, new Position());
        positionMap.put(63, new Position());
        positionMap.put(64, new Position());
        positionMap.put(65, new Position());
        positionMap.put(66, new Position());
        positionMap.put(67, new Position());
        positionMap.put(68, new Position(585, 65));

        positionMap.put(73, new Position());
        positionMap.put(74, new Position());
        positionMap.put(75, new Position());
        positionMap.put(76, new Position());
        positionMap.put(77, new Position());
        positionMap.put(78, new Position());
        positionMap.put(79, new Position());
        positionMap.put(80, new Position());
        positionMap.put(81, new Position());
        positionMap.put(82, new Position());
        positionMap.put(83, new Position());
        positionMap.put(84, new Position());
        positionMap.put(85, new Position());
        positionMap.put(86, new Position());
        positionMap.put(87, new Position());
        positionMap.put(88, new Position());
        positionMap.put(89, new Position());
        positionMap.put(90, new Position());
        positionMap.put(91, new Position());
        positionMap.put(92, new Position());
        positionMap.put(93, new Position());
        positionMap.put(94, new Position());
        positionMap.put(95, new Position());
        positionMap.put(96, new Position());
        positionMap.put(97, new Position());
        positionMap.put(98, new Position());
        positionMap.put(99, new Position());
        positionMap.put(100, new Position());
        positionMap.put(101, new Position());
        positionMap.put(102, new Position());
        positionMap.put(103, new Position());
        positionMap.put(104, new Position());
        // Positions on board.

        // Doubles
        positionMap.put(105, new Position());
        positionMap.put(106, new Position());
        positionMap.put(107, new Position());
        positionMap.put(108, new Position());
        positionMap.put(109, new Position());
        positionMap.put(110, new Position());
        positionMap.put(111, new Position());
        positionMap.put(112, new Position());
        positionMap.put(113, new Position());
        positionMap.put(114, new Position());
        positionMap.put(115, new Position());
        positionMap.put(116, new Position());
        positionMap.put(117, new Position());
        positionMap.put(118, new Position());
        positionMap.put(119, new Position());
        positionMap.put(120, new Position());
        positionMap.put(121, new Position());
        positionMap.put(122, new Position());
        positionMap.put(123, new Position());
        positionMap.put(124, new Position());
        positionMap.put(125, new Position());
        positionMap.put(126, new Position());
        positionMap.put(127, new Position());
        positionMap.put(128, new Position());
        */
    }

    public int getX(int tileNr){
        return positionMap.get(tileNr).getX();
    }

    public int getY(int tileNr){
        return positionMap.get(tileNr).getY();
    }

}
