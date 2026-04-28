public class DigitValidator extends BaseValidator {
    @Override
    public boolean isValid(String key) {
        return key != null && key.matches("^[0-9]{5}$");
    }
}
