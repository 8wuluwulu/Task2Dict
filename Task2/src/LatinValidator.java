public class LatinValidator extends BaseValidator {
    @Override
    public boolean isValid(String key) {
        return key != null && key.matches("^[a-zA-Z]{4}$");
    }
}
