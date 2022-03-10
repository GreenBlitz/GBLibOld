package edu.greenblitz.gblib.hid;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import java.util.HashMap;

/**
 * This class is in charge of making the Joystick easy to assign things to buttons and invert the axes of the joystick.
 * The class uses m_joystick as the main joystick, and uses button values and enums in order to assign thing to those buttons.
 *
 * @see Joystick
 * @see JoystickButton
 */
public class SmartJoystick {
    private Joystick m_joystick;
    private double deadzone;

    private static final double DEADZONE = 0.03;

    private double deadzone(double power) {
        if (Math.abs(power) < deadzone) return 0;
        return (Math.abs(power) - deadzone) / (1 - deadzone) * Math.signum(power);
    }

    public final JoystickButton A,
            B,
            X,
            Y,
            L1,
            R1,
            START,
            BACK,
            L3,
            R3;
    public final POVButton POV_UP,
            POV_RIGHT,
            POV_DOWN,
            POV_LEFT;

    /**
     * This enum is in charge of all joystick axes.
     */
    public enum Axis {
        LEFT_X(0, false),
        LEFT_Y(1, true),
        LEFT_TRIGGER(2, false),
        RIGHT_TRIGGER(3, false),
        RIGHT_X(4, false),
        RIGHT_Y(5, true);

        private int m_axis;
        private int m_inverted;

        Axis(int axis, boolean isInverted) {
            m_axis = axis;
            setInverted(isInverted);
        }

        public void setInverted(boolean isInverted) {
            m_inverted = isInverted ? -1 : 1;
        }

        public double getValue(SmartJoystick stick) {
            return m_inverted * stick.getRawAxis(m_axis);
        }
    }

    /**
     * This constructor constructs the joystick based on the joystick port we give it.
     *
     * @param joystick_port The port of the joystick.
     * @param deadzone      values if the stick below it will count as 0.
     */
    public SmartJoystick(int joystick_port, double deadzone) {
        this(new Joystick(joystick_port), deadzone);
    }

    /**
     * This constructor constructs the joystick based on the joystick port we give it.
     *
     * @param joystick_port The port of the joystick.
     */
    public SmartJoystick(int joystick_port) {
        this(new Joystick(joystick_port));
    }

    /**
     * This constructor uses a joystick and assigns it button values and numbers.
     *
     * @param stick    The joystick object.
     * @param deadzone values if the stick below it will count as 0.
     */
    public SmartJoystick(Joystick stick, double deadzone) {
        this.deadzone = deadzone;
        m_joystick = stick;
        A = new JoystickButton(m_joystick, 1);
        B = new JoystickButton(m_joystick, 2);
        X = new JoystickButton(m_joystick, 3);
        Y = new JoystickButton(m_joystick, 4);
        L1 = new JoystickButton(m_joystick, 5);
        R1 = new JoystickButton(m_joystick, 6);
        BACK = new JoystickButton(m_joystick, 7);
        START = new JoystickButton(m_joystick, 8);
        L3 = new JoystickButton(m_joystick, 9);
        R3 = new JoystickButton(m_joystick, 10);
        POV_UP = new POVButton(m_joystick, 0);
        POV_RIGHT = new POVButton(m_joystick, 90);
        POV_DOWN = new POVButton(m_joystick, 180);
        POV_LEFT = new POVButton(m_joystick, 270);
    }

    public SmartJoystick(Joystick stick) {
        this(stick, DEADZONE);
    }


    /**
     * This function binds a joystick using a joystick object.
     *
     * @param stick This stick object which we bind the joystick to.
     */
    public void bind(Joystick stick) {
        m_joystick = stick;
    }

    /**
     * This function binds a joystick using a joystick port.
     *
     * @param port The port of the joystick which we bind the joystick to.
     */
    public void bind(int port) {
        bind(new Joystick(port));
    }

    /**
     * This function returns the axis based on an axis number.
     *
     * @param raw_axis The axis number we want to return.
     * @return A joystick axis based off of the joystick axis number.
     */
    public double getRawAxis(int raw_axis) {
        if (m_joystick == null) return 0;
        return m_joystick.getRawAxis(raw_axis);
    }

    /**
     * This function returns a joystick
     *
     * @return The joystick used in this class.
     */
    public Joystick getRawJoystick() {
        return m_joystick;
    }

    public double getAxisValue(Axis axis) {
        if (axis != Axis.LEFT_TRIGGER && axis != Axis.RIGHT_TRIGGER)
            return deadzone(axis.getValue(this));
        return axis.getValue(this);
    }

    /**
     * Rumbles the controller at a certain side
     *
     * @param left  rumble side (false for left)
     * @param power normalized rumble [0, 1]
     */
    public void rumble(boolean left, double power) {
        m_joystick.setRumble(left ? GenericHID.RumbleType.kLeftRumble : GenericHID.RumbleType.kRightRumble, power);
        SmartDashboard.putNumber((left ? "left" : "right") + " rumble", power);
    }

    public HashMap<String, Double> getButtonsOn() {
        //todo: for now - 1 == button is on, 0 == button is of (for on/off buttons);
        HashMap<String, Double> buttons = new HashMap<String, Double>();
        // A, B, X, Y, L1, R1, START, BACK, L3, R3, POV_UP, POV_RIGHT, POV_DOWN, POV_LEFT;
        buttons.put("A", A.get() ? 1.0 : 0.0);
        buttons.put("B", B.get() ? 1.0 : 0.0);
        buttons.put("X", X.get() ? 1.0 : 0.0);
        buttons.put("Y", Y.get() ? 1.0 : 0.0);
        buttons.put("L1", L1.get() ? 1.0 : 0.0);
        buttons.put("R1", R1.get() ? 1.0 : 0.0);
        buttons.put("START", START.get() ? 1.0 : 0.0);
        buttons.put("BACK", BACK.get() ? 1.0 : 0.0);
        buttons.put("L3", L3.get() ? 1.0 : 0.0);
        buttons.put("POV_UP", POV_UP.get() ? 1.0 : 0.0);
        buttons.put("POV_RIGHT", POV_RIGHT.get() ? 1.0 : 0.0);
        buttons.put("POV_DOWN", POV_DOWN.get() ? 1.0 : 0.0);
        buttons.put("POV_LEFT", POV_LEFT.get() ? 1.0 : 0.0);
        buttons.put("LEFT_X", Axis.LEFT_X.getValue(this));
        buttons.put("LEFT_Y", Axis.LEFT_Y.getValue(this));
        buttons.put("LEFT_TRIGGER", Axis.LEFT_TRIGGER.getValue(this));
        buttons.put("RIGHT_TRIGGER", Axis.RIGHT_TRIGGER.getValue(this));
        buttons.put("RIGHT_X", Axis.RIGHT_X.getValue(this));
        buttons.put("RIGHT_Y", Axis.RIGHT_Y.getValue(this));
        return buttons;
    }
}