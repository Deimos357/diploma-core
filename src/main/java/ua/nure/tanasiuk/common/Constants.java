package ua.nure.tanasiuk.common;

import java.util.regex.Pattern;

public final class Constants {
    private Constants() {
        throw new AssertionError();
    }

    public static final String INVALID_USERS_WERE_RECEIVED = "invalid users were received";
    public static final String INVALID_DEVICES_WERE_RECEIVED = "invalid devices were received";
    public static final String DEFAULT_LIMIT = "50";
    public static final String DEFAULT_OFFSET = "0";

    public static class ValidationProperties {
        public static final int MAX_USER_NUMBER_ON_GET_REQUEST = 100;
        public static final String PHONE_PATTERN = "\\+?\\d{6,25}";
        public static final int MAX_PHONES_SEARCH_SIZE = 500;
    }

    public static class UserProfile {
        public static final String FACEBOOK_URL = "https://www.facebook.com/";
        public static final String DATE_FORMAT = "MM/dd/yyyy";
        public static final int USER_AND_PASS_LENGTH_MAX = 64;
        public static final int USERNAME_LENGTH_MIN = 2;
        public static final String ENCODED_PASSWORD_PATTERN = "[a-z0-9]{64}";
        public static final int ABOUTME_LENGTH_MAX = 500;
        public static final int SIX_YEARS = 2190;
        public static final String PHONE_REGEXP = "^\\+?\\d{6,25}$";
        public static final String ALL_EXCEPT_LINKS_PATTERN = "(?s)((?!(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})"
            + "([\\/\\w \\.-]*)*\\/?).)*";
        public static final int FRIENDS_COUNT_ON_USER_PROFILE = 4;
        public static final int ACTIVE_AVATAR_DEFAULT_PRIORITY = 1;
    }

    public static class Place {
        public static final int MIN_NAME_LENGTH = 2;
        public static final int MAX_NAME_LENGTH = 40;
    }

    public static class GatorDevice {
        public static final String IMEI_LENGTH_PATTERN = ".{15}";
        public static final int MIN_CODE_LENGTH = 1;
        public static final String FAILED_TO_UPDATE_POSITIONS_MESSAGE = "Failed to update device positions fro user with id {}";
        public static final int MAX_GATOR_ALARM_INDEX = 4;
        public static final String CONTACT_LIST_PHONE_NUMBER_REGEX = "\\+?\\d{3,20}";
        public static final int CONTACT_NAME_MIN_LENGTH = 1;
        public static final int CONTACT_NAME_MAX_LENGTH = 9;
        public static final String NORWAY_PHONE_NUMBER_REGEX = "^\\+47\\d{8}";
        public static final Integer WIFI_NUMBER = 2;
        public static final String MAC_ADDRESS_PATTERN = "^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$";
    }

    public static class AmazonS3 {
        public static final String AMAZON_PATH_SEPARATOR = "/";
        public static final String AMAZON_PLACE_FOLDER = "place";
        public static final String AMAZON_AVATAR_FOLDER = "avatar";
        public static final String AMAZON_DEVICE_FOLDER = "device";
        public static final String AMAZON_POST_FOLDER = "post";
        public static final String MIN_PHOTO_SUFFIX = "-min";
        public static final String VALID_EXTENSIONS_PATTERN = "jpeg|jpg";
        public static final String DEFAULT_EXTENSION = "jpg";
        public static final Pattern S3_LINK_PATTERN = Pattern
            .compile(".*(devimages|imagepreprod|imageprod)\\.teleg\\.no\\/(.*)");
        public static final String AMAZON_DEVICE_CONTACTS_FOLDER = "contacts";
    }

    public static class GoogleMaps {
        public static final String DEFAULT_PLACE_SNAPSHOT_FORMAT = ".png";
        public static final int EARTH_RADIUS_IN_METERS = 6371000;
        public static final double METERS_PER_PIXEL_ON_ZERO_ZOOM = 156543.03392;
    }

    public static class ErrorCodes {
        public static final String GENERAL_ERROR = "400009";
        public static final String ARE_NOT_FRIENDS = "400013";
        public static final String WRONG_USERNAME_LENGTH = "400019";
        public static final String WRONG_ENCODED_PASSWORD_LENGTH = "400020";
        public static final String PHONE_NUMBER_ALREADY_IN_USE = "400021";
        public static final String NOT_ALLOWED_PUBLIC_VISIBILITY = "400023";
        public static final String IS_NOT_A_VALID_PHONE_NUMBER = "400024";
        public static final String TWILLO_ERROR = "400025";
        public static final String IS_NOT_A_MOBILE_NUMBER = "400026";
        public static final String WRONG_USER_NUMBER_IN_REQUEST = "400027";
        public static final String FACEBOOK_ACCOUNT_ALREADY_EXIST = "400028";
        public static final String WRONG_OLD_PASSWORD = "400029";
        public static final String NON_EXISTENT_PHONE = "400030";
        public static final String WRONG_SEARCH_REQUEST_LENGTH = "400033";
        public static final String INVALID_TOKEN = "400034";
        public static final String INVALID_TOKEN_OR_CODE = "400035";
        public static final String INVALID_VALIDATION_CODE = "400036";
        public static final String VALIDATION_CODE_EXPIRED = "400037";
        public static final String WRONG_PHONE_NUMBER_IN_REQUEST = "400038";
        public static final String USER_IS_BLOCKED = "400039";
        public static final String MAX_PHONES_SIZE_IN_REQUEST_EXCEEDED = "400040";
        public static final String WRONG_BIRTHDAY = "400041";
        public static final String WRONG_ABOUTME_LENGTH = "400043";
        public static final String ALREADY_REPORTED_USER = "400045";
        public static final String TRY_SMS_AGAIN_LATER = "400048";
        public static final String FAILED_TO_UPDATE_POSITIONS = "400049";
        public static final String LIMIT_IS_EXCEEDED = "400051";
        public static final String PHONE_NUMBER_CANT_BE_SET_TO_NULL = "400052";
        public static final String AVATAR_LIMIT = "400053";
        public static final String INCORRECT_ABOUT_ME = "400054";
        public static final String ALARM_LIMIT = "400055";
        public static final String PHOTO_NOT_EXIST = "400056";
        public static final String MAX_CONFIRMATION_CODE_RETRY_COUNT_EXCEEDED = "400057";
        public static final String IS_NOT_A_NORWAY_PHONE_NUMBER = "400058";
        public static final String WRONG_GATOR_WIFI_MAC = "400059";
        public static final String WRONG_GATOR_WIFI_NAME = "400061";
        public static final String EQUAL_MAC_ADDRESSES = "400062";
    }

    public static class MQProperties {
        public static final String ROUTING_KEY = null;
    }
}
