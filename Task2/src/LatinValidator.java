public class LatinValidator extends BaseValidator {
    public boolean isValid(String key) {
        return key != null && key.matches("^[a-zA-Z]{4}$");
    }
}
