import org.jetbrains.annotations.NotNull;

/**
 * В теле класса решения разрешено использовать только финальные переменные типа RegularInt.
 * Нельзя volatile, нельзя другие типы, нельзя блокировки, нельзя лазить в глобальные переменные.
 *
 * @author Nazarov Georgiy
 */
public class Solution implements MonotonicClock {
    private final RegularInt a1 = new RegularInt(0);
    private final RegularInt a2 = new RegularInt(0);
    private final RegularInt a3 = new RegularInt(0);
    private final RegularInt b1 = new RegularInt(0);
    private final RegularInt b2 = new RegularInt(0);
    private final RegularInt b3 = new RegularInt(0);

    @Override
    public void write(@NotNull Time time) {
        b1.setValue(time.getD1());
        b2.setValue(time.getD2());
        b3.setValue(time.getD3());

        a3.setValue(time.getD3());
        a2.setValue(time.getD2());
        a1.setValue(time.getD1());
    }

    @NotNull
    @Override
    public Time read() {
        final RegularInt r11 = new RegularInt(0), r12 = new RegularInt(0), r13 = new RegularInt(0),
                         r21 = new RegularInt(0), r22 = new RegularInt(0), r23 = new RegularInt(0);
        r11.setValue(a1.getValue());
        r12.setValue(a2.getValue());
        r13.setValue(a3.getValue());
        r23.setValue(b3.getValue());
        r22.setValue(b2.getValue());
        r21.setValue(b1.getValue());

        if (r11.getValue() == r21.getValue() && r12.getValue() == r22.getValue() && r13.getValue() == r23.getValue())
            return new Time(r11.getValue(), r12.getValue(), r13.getValue());
        else {
            if (r11.getValue() == r21.getValue()) {
                if (r12.getValue() == r22.getValue()) {
                    return new Time(r11.getValue(), r12.getValue(), r23.getValue());
                } else {
                    return new Time(r11.getValue(), r22.getValue(), 0);
                }
            } else {
                return new Time(r21.getValue(), 0, 0);
            }
        }
    }
}
