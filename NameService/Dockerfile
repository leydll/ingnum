FROM php:8.2-apache
COPY index.php /var/www/html/
COPY .htaccess /var/www/html/
RUN a2enmod rewrite
RUN sed -i '/<Directory \/var\/www\/>/,/<\/Directory>/ s/AllowOverride None/AllowOverride All/' /etc/apache2/apache2.conf
EXPOSE 80