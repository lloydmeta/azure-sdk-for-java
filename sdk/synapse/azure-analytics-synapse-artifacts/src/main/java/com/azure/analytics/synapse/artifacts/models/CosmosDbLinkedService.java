// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Microsoft Azure Cosmos Database (CosmosDB) linked service.
 */
@Fluent
public class CosmosDbLinkedService extends LinkedService {
    /*
     * Type of linked service.
     */
    @Generated
    private String type = "CosmosDb";

    /*
     * The connection string. Type: string, SecureString or AzureKeyVaultSecretReference.
     */
    @Generated
    private Object connectionString;

    /*
     * The endpoint of the Azure CosmosDB account. Type: string (or Expression with resultType string)
     */
    @Generated
    private Object accountEndpoint;

    /*
     * The name of the database. Type: string (or Expression with resultType string)
     */
    @Generated
    private Object database;

    /*
     * The account key of the Azure CosmosDB account. Type: SecureString or AzureKeyVaultSecretReference.
     */
    @Generated
    private SecretBase accountKey;

    /*
     * The encrypted credential used for authentication. Credentials are encrypted using the integration runtime
     * credential manager. Type: string (or Expression with resultType string).
     */
    @Generated
    private Object encryptedCredential;

    /*
     * The credential reference containing authentication information.
     */
    @Generated
    private CredentialReference credential;

    /**
     * Creates an instance of CosmosDbLinkedService class.
     */
    @Generated
    public CosmosDbLinkedService() {
    }

    /**
     * Get the type property: Type of linked service.
     * 
     * @return the type value.
     */
    @Generated
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Get the connectionString property: The connection string. Type: string, SecureString or
     * AzureKeyVaultSecretReference.
     * 
     * @return the connectionString value.
     */
    @Generated
    public Object getConnectionString() {
        return this.connectionString;
    }

    /**
     * Set the connectionString property: The connection string. Type: string, SecureString or
     * AzureKeyVaultSecretReference.
     * 
     * @param connectionString the connectionString value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setConnectionString(Object connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    /**
     * Get the accountEndpoint property: The endpoint of the Azure CosmosDB account. Type: string (or Expression with
     * resultType string).
     * 
     * @return the accountEndpoint value.
     */
    @Generated
    public Object getAccountEndpoint() {
        return this.accountEndpoint;
    }

    /**
     * Set the accountEndpoint property: The endpoint of the Azure CosmosDB account. Type: string (or Expression with
     * resultType string).
     * 
     * @param accountEndpoint the accountEndpoint value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setAccountEndpoint(Object accountEndpoint) {
        this.accountEndpoint = accountEndpoint;
        return this;
    }

    /**
     * Get the database property: The name of the database. Type: string (or Expression with resultType string).
     * 
     * @return the database value.
     */
    @Generated
    public Object getDatabase() {
        return this.database;
    }

    /**
     * Set the database property: The name of the database. Type: string (or Expression with resultType string).
     * 
     * @param database the database value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setDatabase(Object database) {
        this.database = database;
        return this;
    }

    /**
     * Get the accountKey property: The account key of the Azure CosmosDB account. Type: SecureString or
     * AzureKeyVaultSecretReference.
     * 
     * @return the accountKey value.
     */
    @Generated
    public SecretBase getAccountKey() {
        return this.accountKey;
    }

    /**
     * Set the accountKey property: The account key of the Azure CosmosDB account. Type: SecureString or
     * AzureKeyVaultSecretReference.
     * 
     * @param accountKey the accountKey value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setAccountKey(SecretBase accountKey) {
        this.accountKey = accountKey;
        return this;
    }

    /**
     * Get the encryptedCredential property: The encrypted credential used for authentication. Credentials are encrypted
     * using the integration runtime credential manager. Type: string (or Expression with resultType string).
     * 
     * @return the encryptedCredential value.
     */
    @Generated
    public Object getEncryptedCredential() {
        return this.encryptedCredential;
    }

    /**
     * Set the encryptedCredential property: The encrypted credential used for authentication. Credentials are encrypted
     * using the integration runtime credential manager. Type: string (or Expression with resultType string).
     * 
     * @param encryptedCredential the encryptedCredential value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setEncryptedCredential(Object encryptedCredential) {
        this.encryptedCredential = encryptedCredential;
        return this;
    }

    /**
     * Get the credential property: The credential reference containing authentication information.
     * 
     * @return the credential value.
     */
    @Generated
    public CredentialReference getCredential() {
        return this.credential;
    }

    /**
     * Set the credential property: The credential reference containing authentication information.
     * 
     * @param credential the credential value to set.
     * @return the CosmosDbLinkedService object itself.
     */
    @Generated
    public CosmosDbLinkedService setCredential(CredentialReference credential) {
        this.credential = credential;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public CosmosDbLinkedService setVersion(String version) {
        super.setVersion(version);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public CosmosDbLinkedService setConnectVia(IntegrationRuntimeReference connectVia) {
        super.setConnectVia(connectVia);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public CosmosDbLinkedService setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public CosmosDbLinkedService setParameters(Map<String, ParameterSpecification> parameters) {
        super.setParameters(parameters);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public CosmosDbLinkedService setAnnotations(List<Object> annotations) {
        super.setAnnotations(annotations);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("version", getVersion());
        jsonWriter.writeJsonField("connectVia", getConnectVia());
        jsonWriter.writeStringField("description", getDescription());
        jsonWriter.writeMapField("parameters", getParameters(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("annotations", getAnnotations(), (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeStringField("type", this.type);
        if (connectionString != null
            || accountEndpoint != null
            || database != null
            || accountKey != null
            || encryptedCredential != null
            || credential != null) {
            jsonWriter.writeStartObject("typeProperties");
            if (this.connectionString != null) {
                jsonWriter.writeUntypedField("connectionString", this.connectionString);
            }
            if (this.accountEndpoint != null) {
                jsonWriter.writeUntypedField("accountEndpoint", this.accountEndpoint);
            }
            if (this.database != null) {
                jsonWriter.writeUntypedField("database", this.database);
            }
            jsonWriter.writeJsonField("accountKey", this.accountKey);
            if (this.encryptedCredential != null) {
                jsonWriter.writeUntypedField("encryptedCredential", this.encryptedCredential);
            }
            jsonWriter.writeJsonField("credential", this.credential);
            jsonWriter.writeEndObject();
        }
        if (getAdditionalProperties() != null) {
            for (Map.Entry<String, Object> additionalProperty : getAdditionalProperties().entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CosmosDbLinkedService from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of CosmosDbLinkedService if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the CosmosDbLinkedService.
     */
    @Generated
    public static CosmosDbLinkedService fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            CosmosDbLinkedService deserializedCosmosDbLinkedService = new CosmosDbLinkedService();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("version".equals(fieldName)) {
                    deserializedCosmosDbLinkedService.setVersion(reader.getString());
                } else if ("connectVia".equals(fieldName)) {
                    deserializedCosmosDbLinkedService.setConnectVia(IntegrationRuntimeReference.fromJson(reader));
                } else if ("description".equals(fieldName)) {
                    deserializedCosmosDbLinkedService.setDescription(reader.getString());
                } else if ("parameters".equals(fieldName)) {
                    Map<String, ParameterSpecification> parameters
                        = reader.readMap(reader1 -> ParameterSpecification.fromJson(reader1));
                    deserializedCosmosDbLinkedService.setParameters(parameters);
                } else if ("annotations".equals(fieldName)) {
                    List<Object> annotations = reader.readArray(reader1 -> reader1.readUntyped());
                    deserializedCosmosDbLinkedService.setAnnotations(annotations);
                } else if ("type".equals(fieldName)) {
                    deserializedCosmosDbLinkedService.type = reader.getString();
                } else if ("typeProperties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("connectionString".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.connectionString = reader.readUntyped();
                        } else if ("accountEndpoint".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.accountEndpoint = reader.readUntyped();
                        } else if ("database".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.database = reader.readUntyped();
                        } else if ("accountKey".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.accountKey = SecretBase.fromJson(reader);
                        } else if ("encryptedCredential".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.encryptedCredential = reader.readUntyped();
                        } else if ("credential".equals(fieldName)) {
                            deserializedCosmosDbLinkedService.credential = CredentialReference.fromJson(reader);
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedCosmosDbLinkedService.setAdditionalProperties(additionalProperties);

            return deserializedCosmosDbLinkedService;
        });
    }
}
