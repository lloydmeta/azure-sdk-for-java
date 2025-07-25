// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The metadata of the SQL script.
 */
@Fluent
public final class SqlScriptMetadata implements JsonSerializable<SqlScriptMetadata> {
    /*
     * The language of the SQL script.
     */
    @Generated
    private String language;

    /*
     * The metadata of the SQL script.
     */
    @Generated
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of SqlScriptMetadata class.
     */
    @Generated
    public SqlScriptMetadata() {
    }

    /**
     * Get the language property: The language of the SQL script.
     * 
     * @return the language value.
     */
    @Generated
    public String getLanguage() {
        return this.language;
    }

    /**
     * Set the language property: The language of the SQL script.
     * 
     * @param language the language value to set.
     * @return the SqlScriptMetadata object itself.
     */
    @Generated
    public SqlScriptMetadata setLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * Get the additionalProperties property: The metadata of the SQL script.
     * 
     * @return the additionalProperties value.
     */
    @Generated
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: The metadata of the SQL script.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the SqlScriptMetadata object itself.
     */
    @Generated
    public SqlScriptMetadata setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("language", this.language);
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SqlScriptMetadata from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SqlScriptMetadata if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the SqlScriptMetadata.
     */
    @Generated
    public static SqlScriptMetadata fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SqlScriptMetadata deserializedSqlScriptMetadata = new SqlScriptMetadata();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("language".equals(fieldName)) {
                    deserializedSqlScriptMetadata.language = reader.getString();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedSqlScriptMetadata.additionalProperties = additionalProperties;

            return deserializedSqlScriptMetadata;
        });
    }
}
