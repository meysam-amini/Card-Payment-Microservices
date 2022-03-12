package bank.sp1.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransferDto {

    private String firstCard;

    private String secondCard;

    private Long amount;

    private LocalDate Date;
}
