package ru.itmo.sdcourse.hw9.search.engine.vk.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.Validate;
import ru.itmo.sdcourse.hw9.search.engine.vk.exception.VkException;
import ru.itmo.sdcourse.hw9.search.engine.vk.response.VkNewsfeedSearchResponse;

import java.util.ArrayList;
import java.util.List;

public class VkNewsfeedSearchResponseParser implements VkResponseParser<VkNewsfeedSearchResponse> {
    private static final String RESPONSE_MEMBER_NAME = "response";
    private static final String ID_MEMBER_NAME = "id";
    private static final String OWNER_ID_MEMBER_NAME = "owner_id";
    private static final String ITEMS_MEMBER_NAME = "items";
    private static final String TEXT_MEMBER_NAME = "text";

    private static final String ERROR_MEMBER_NAME = "error";
    private static final String ERROR_MESSAGE_MEMBER_NAME = "error_msg";

    @Override
    public VkNewsfeedSearchResponse parse(String apiResponse) {
        JsonElement rootJsonElement = JsonParser.parseString(apiResponse);
        var rootJsonObject = rootJsonElement.getAsJsonObject();
        if (!rootJsonObject.has(RESPONSE_MEMBER_NAME))
            throwApiException(rootJsonObject);

        var jsonObject = rootJsonObject.getAsJsonObject(RESPONSE_MEMBER_NAME);
        checkHasMember(jsonObject, ITEMS_MEMBER_NAME);

        var itemsJsonArray = jsonObject.getAsJsonArray(ITEMS_MEMBER_NAME);
        List<VkNewsfeedSearchResponse.Item> result = new ArrayList<>(itemsJsonArray.size());
        for (JsonElement itemJsonElement : itemsJsonArray) {
            var itemJsonObject = itemJsonElement.getAsJsonObject();
            checkHasMember(itemJsonObject, ID_MEMBER_NAME);
            checkHasMember(itemJsonObject, OWNER_ID_MEMBER_NAME);
            checkHasMember(itemJsonObject, TEXT_MEMBER_NAME);

            result.add(new VkNewsfeedSearchResponse.Item(
                    itemJsonObject.getAsJsonPrimitive(ID_MEMBER_NAME).getAsLong(),
                    itemJsonObject.getAsJsonPrimitive(OWNER_ID_MEMBER_NAME).getAsLong(),
                    itemJsonObject.getAsJsonPrimitive(TEXT_MEMBER_NAME).getAsString()
            ));
        }

        return new VkNewsfeedSearchResponse(result);
    }

    private void throwApiException(JsonObject rootJsonObject) {
        checkHasMember(rootJsonObject, ERROR_MEMBER_NAME);
        JsonObject errorJsonObject = rootJsonObject.getAsJsonObject(ERROR_MEMBER_NAME);
        checkHasMember(errorJsonObject, ERROR_MESSAGE_MEMBER_NAME);
        throw new VkException(errorJsonObject.getAsJsonPrimitive(ERROR_MESSAGE_MEMBER_NAME).getAsString());
    }

    private void checkHasMember(JsonObject jsonObject, String member) {
        Validate.isTrue(jsonObject.has(member), "Expected '%s' in json", member);
    }
}
