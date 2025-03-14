GRADLE := ./gradlew

fmt:
	$(GRADLE) spotlessApply
