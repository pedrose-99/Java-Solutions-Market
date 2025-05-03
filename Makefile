.PHONY: start start-backend start-frontend stop clean install-deps-linux install-deps-windows check-deps-linux check-deps-windows

BACKEND_CMD=spring-boot:run
FRONTEND_CMD=ng serve

BACKEND_URL=http://localhost:8080
FRONTEND_URL=http://localhost:4200

start: check-deps
	@echo "🔄 Iniciando backend y frontend en nuevas terminales..."
	@$(MAKE) start-backend
	@$(MAKE) start-frontend
	@echo ""
	@echo "🟢 Accesos rápidos:"
	@echo "🌐 Angular:  $(FRONTEND_URL)"
	@echo "🚀 Backend:  $(BACKEND_URL)"
	@echo "✅ Ambos procesos iniciados."

start-backend:
	@echo "🚀 Iniciando Spring Boot en una nueva terminal..."
	gnome-terminal -- bash -c "cd CRM-BACKEND && ./mvnw spring-boot:run; exec bash"

start-frontend:
	@echo "🌐 Iniciando Angular en una nueva terminal..."
	gnome-terminal -- bash -c "cd CRM-FRONT && ng serve; exec bash"

check-deps-linux:
	@echo "🔎 Comprobando dependencias para Linux..."
	@which node > /dev/null && echo "✅ Node.js está instalado." || (echo "❌ Node.js no está instalado. Instálalo desde https://nodejs.org/." && exit 1)
	@which npm > /dev/null && echo "✅ npm está instalado." || (echo "❌ npm no está instalado. Instálalo junto con Node.js." && exit 1)

check-deps-windows:
	@echo "🔎 Comprobando dependencias para Windows..."
	@where node > nul && echo "✅ Node.js está instalado." || (echo "❌ Node.js no está instalado. Instálalo desde https://nodejs.org/." && exit 1)
	@where npm > nul && echo "✅ npm está instalado." || (echo "❌ npm no está instalado. Instálalo junto con Node.js." && exit 1)
	@npm list --global windows-build-tools > nul 2>&1 && echo "✅ Herramientas de compilación de Windows están instaladas." || (echo "❌ Las herramientas de compilación de Windows no están instaladas. Ejecuta 'npm install --global windows-build-tools'." && exit 1)

install-deps-linux:
	@if [ ! -d "CRM-FRONT/node_modules" ]; then \
		echo "📦 Instalando dependencias de Angular para Linux..."; \
		cd CRM-FRONT && npm install; \
	else \
		echo "📦 Dependencias de Angular ya instaladas en Linux."; \
	fi

install-deps-windows:
	@if [ ! -d "CRM-FRONT\node_modules" ]; then \
		echo "📦 Instalando dependencias de Angular para Windows..."; \
		cd CRM-FRONT && npm install; \
	else \
		echo "📦 Dependencias de Angular ya instaladas en Windows."; \
	fi

check-deps:
	@if uname -s | grep -i "linux" > /dev/null; then \
		$(MAKE) check-deps-linux; \
	elif uname -s | grep -i "mingw" > /dev/null || uname -s | grep -i "cygwin" > /dev/null; then \
		$(MAKE) check-deps-windows; \
	else \
		echo "❌ Sistema no reconocido. No se puede comprobar las dependencias."; \
		exit 1; \
	fi

stop:
	@echo "🛑 Deteniendo procesos..."
	# Matar el proceso frontend (ng serve) usando el PID
	@echo "🚀 Deteniendo Angular..."
	@lsof -ti:4200 | xargs kill -9 
	@echo "✅ Procesos detenidos."
	# Matar el proceso backend
	@pkill -f "$(BACKEND_CMD)" || true

clean:
	@echo "🧹 Limpiando archivos de log..."
	rm -f backend.log frontend.log
	@echo "✅ Limpieza completada."
