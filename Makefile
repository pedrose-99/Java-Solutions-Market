.PHONY: start start-windows start-backend start-frontend stop clean install-deps-linux install-deps-windows check-deps-linux check-deps-windows

BACKEND_CMD=spring-boot:run
FRONTEND_CMD=npm run dev

BACKEND_URL=http://localhost:8080
FRONTEND_URL=http://localhost:5173

start: check-deps
	@echo "🔄 Iniciando backend y frontend en nuevas terminales..."
	@$(MAKE) start-backend
	@$(MAKE) start-frontend
	@echo ""
	@echo "🟢 Accesos rápidos:"
	@echo "🌐 React (Vite): $(FRONTEND_URL)"
	@echo "🚀 Backend:       $(BACKEND_URL)"
	@echo "✅ Ambos procesos iniciados."

start-windows:
	@echo "🔄 Iniciando backend y frontend en Git Bash..."
	@cd /c/Users/Silvi/OneDrive/Documentos/GitHub/Java-Solutions-Market/CRM-BACKEND && start "" "C:/Program Files/Git/bin/bash.exe" -c "./mvnw spring-boot:run; exec bash"
	@cd /c/Users/Silvi/OneDrive/Documentos/GitHub/Java-Solutions-Market/CRM-FRONTEND && start "" "C:/Program Files/Git/bin/bash.exe" -c "npm install && npm run dev; exec bash"
	@timeout /t 2 > nul
	@echo ""
	@echo "🟢 Accesos rápidos:"
	@echo "🌐 React (Vite): $(FRONTEND_URL)"
	@echo "🚀 Backend:       $(BACKEND_URL)"
	@echo "✅ Ambos procesos iniciados en terminales de Git Bash."

start-backend:
	@echo "🚀 Iniciando Spring Boot en una nueva terminal..."
	gnome-terminal -- bash -c "cd CRM-BACKEND && ./mvnw spring-boot:run; exec bash"

start-frontend:
	@echo "🌐 Iniciando React con Vite en una nueva terminal..."
	gnome-terminal -- bash -c "cd CRM-FRONTEND && npm run dev; exec bash"

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
	@if [ ! -d "CRM-FRONTEND/node_modules" ]; then \
		echo "📦 Instalando dependencias de React (Vite) para Linux..."; \
		cd CRM-FRONTEND && npm install; \
	else \
		echo "📦 Dependencias de React ya instaladas en Linux."; \
	fi

install-deps-windows:
	@if [ ! -d "CRM-FRONTEND\\node_modules" ]; then \
		echo "📦 Instalando dependencias de React (Vite) para Windows..."; \
		cd CRM-FRONTEND && npm install; \
	else \
		echo "📦 Dependencias de React ya instaladas en Windows."; \
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
	@echo "🚀 Deteniendo React (Vite)..."
	@lsof -ti:5173 | xargs kill -9 || echo "⚠️ No se encontró React (Vite) corriendo en el puerto 5173"
	@pkill -f "$(BACKEND_CMD)" || true
	@echo "✅ Procesos detenidos."

clean:
	@echo "🧹 Limpiando archivos de log..."
	rm -f backend.log frontend.log
	@echo "✅ Limpieza completada."
