package com.avit.bindings;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivateAccount {
private String email;
private String tempPwd;
private String newPwd;
private String confirmPwd;

}
