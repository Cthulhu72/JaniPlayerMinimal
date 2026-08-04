- name: Checkout
  uses: actions/checkout@v3

- name: Make gradlew executable
  run: chmod +x ./gradlew

- name: Set up JDK
  uses: actions/setup-java@v3
  with:
    distribution: 'temurin'
    java-version: '17'

- name: Build Release Apk
  run: ./gradlew assembleRelease
