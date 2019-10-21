package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {

    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    @FXML
    private TextField priceTextField;

    @FXML
    private Label percentageLabel;

    @FXML
    private Slider percentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(priceTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            priceTextField.setText("Enter amount");
            priceTextField.selectAll();
            priceTextField.requestFocus();
        }
    }

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        percentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        tipPercentage =
                                BigDecimal.valueOf(newValue.intValue() / 100.0);
                        percentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
}