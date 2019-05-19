//
// Built on Tue Apr 18 13:44:32 UTC 2017 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.boolex.GEventEvaluator
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.filter.EvaluatorFilter

import static ch.qos.logback.classic.Level.ALL
import static ch.qos.logback.classic.Level.WARN
import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

statusListener(OnConsoleStatusListener)

def PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

def TMPDIR = System.getProperty("java.io.tmpdir")
println "TMPDIR=${TMPDIR}"

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %boldBlue(%.-1level) %logger{36} - %msg%n"
  }
}
appender("FILE1", FileAppender) {
  file = "${TMPDIR}/messages.log"
  append = false
  filter(ThresholdFilter) {
    level = WARN
  }
  encoder(PatternLayoutEncoder) {
    pattern = PATTERN
  }
}
appender("FILE2", FileAppender) {
  file = "${TMPDIR}/warnings.log"
  append = false
  filter(LevelFilter) {
    level = WARN
    onMatch = ACCEPT
    onMismatch = DENY
  }
  encoder(PatternLayoutEncoder) {
    pattern = PATTERN
  }
}
appender("FILE3", FileAppender) {
  file = "${TMPDIR}/fail.log"
  append = false
  filter(EvaluatorFilter) {
    evaluator(GEventEvaluator) {
      expression = 'e.getMessage().toLowerCase().contains("fail")'
    }
    onMatch = ACCEPT
    onMismatch = DENY
  }
  encoder(PatternLayoutEncoder) {
    pattern = PATTERN
  }
}
root(ALL, ["STDOUT", "FILE1", "FILE2", "FILE3"])
logger("pkg.Main", ALL, ["STDOUT"], true)
