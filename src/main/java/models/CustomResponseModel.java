package models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class CustomResponseModel {
    private final int statusCode;
    @NonNull
    private final String responseString;
    private HashMap<String, Object> responseMap;
    private List<Map<String, Object>> responseList;
}
