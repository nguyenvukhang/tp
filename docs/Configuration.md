---
layout: page
title: Configuration guide
---

# Project Configuration Guide

This document provides a comprehensive guide to configuring Checkstyle, Eclipse, and suppression rules.

## Checkstyle Configuration

### Checkstyle Rules
Checkstyle is configured using the `checkstyle.xml` file. It enforces coding standards to maintain code consistency and quality.

#### Location:
- `config/checkstyle/checkstyle.xml`

#### Key Rules:
- Enforces naming conventions for classes, methods, and variables.
- Limits line length to a number of characters.
- Checks Javadoc comments.
- Ensures proper indentation and whitespace usage.
- Enforces bracket styles and statement formatting.

Refer the tutorial Using [Checkstyle @SE-EDU/guides](https://se-education.org/guides/tutorials/checkstyle.html)

## Suppression Rules

### Suppressions Configuration
To ignore certain Checkstyle warnings in specific cases, the `suppressions.xml` file is used.

#### Location:
- `config/checkstyle/suppressions.xml`

#### Example Suppression:
```xml
<suppress checks="MethodNameCheck" files=".*Test.java" />
```
This suppresses method name warnings for test files.

## Eclipse Configuration

### Eclipse Formatter
The project follows a specific code formatting style, enforced using an Eclipse formatter file.

#### Location:
- `.settings/org.eclipse.jdt.core.prefs`
- `.settings/org.eclipse.jdt.ui.prefs`

#### Applying Eclipse Formatting:
1. Import the formatter settings in Eclipse:
   - Go to **Preferences** → **Java** → **Code Style** → **Formatter**.
   - Click **Import** and select the provided formatter file.
2. Enable auto-formatting on save:
   - Go to **Preferences** → **Java** → **Editor** → **Save Actions**.
   - Enable **Format source code**.

## Additional Notes
- Ensure all team members uses the same configuration for consistency.
- Periodically update the configuration files to reflect changes.
- For automated enforcement, Checkstyle has been integrated into Github build pipeline.

---
This guide ensures that all developers follow the same coding standards and maintain high-quality code throughout the project.

