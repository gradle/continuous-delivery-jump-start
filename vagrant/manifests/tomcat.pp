group { 'puppet':
  ensure => 'present'
}

exec { "update-package-list":
  command => "/usr/bin/sudo /usr/bin/apt-get update",
}

class java_7::install {
  package { "openjdk-7-jdk":
    ensure => installed
  }
}

include java_7::install

class tomcat7 {
  package { 'tomcat7':
    ensure => installed
  }
  
  package { 'tomcat7-admin':
    ensure => installed,
    require => Package['tomcat7'],
  }
  
  service { 'tomcat7':
    ensure => running,
    require => Package['tomcat7'],
  } 
  
  file { "/etc/tomcat7/tomcat-users.xml": 
    require => Package['tomcat7'],
    notify => Service['tomcat7'],
    source => "/vagrant/files/tomcat-users.xml",
  }
}

include tomcat7

Exec["update-package-list"] -> Class["java_7::install"] -> Class["tomcat7"]