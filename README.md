# 🔔 aiwa-toast

APK auxiliar mínimo para exibir **Toast notifications** na TV Android via ADB.  
Desenvolvido para funcionar em conjunto com o [AIWA Pro Remote](https://github.com/eoshai/aiwa-pro-remote).

---

## Por que esse APK existe?

O Android TV bloqueia a exibição visual de notificações enviadas pelo shell ADB — o canal `shell_cmd` existe mas é filtrado pela ROM. Este APK registra um `BroadcastReceiver` próprio que o ADB consegue acionar diretamente, exibindo um Toast nativo na tela independente da fonte ativa (HDMI, TV ao vivo, etc).

---

## 📲 Instalação

Baixe o APK compilado na aba **Actions** deste repositório:

1. Clique em **Actions** → última build verde
2. Em **Artifacts**, baixe `aiwa-toast`
3. Extraia o `.zip` — dentro está o `app-debug.apk`
4. Instale na TV via ADB:

```bash
adb install app-debug.apk
```

Confirme a instalação:
```bash
adb shell pm list packages | findstr aiwa
# Deve retornar: package:com.aiwa.remote.toast
```

---

## 🚀 Uso

### Toast curto (~2 segundos)
```bash
adb shell am broadcast \
  -a com.aiwa.SHOW_TOAST \
  -n com.aiwa.remote.toast/.ToastReceiver \
  --es message "Sua mensagem aqui" \
  --ez long false
```

### Toast longo (~3.5 segundos)
```bash
adb shell am broadcast \
  -a com.aiwa.SHOW_TOAST \
  -n com.aiwa.remote.toast/.ToastReceiver \
  --es message "Sua mensagem aqui" \
  --ez long true
```

### No Windows (cmd) — use `^` para quebrar linha
```bash
adb shell am broadcast ^
  -a com.aiwa.SHOW_TOAST ^
  -n com.aiwa.remote.toast/.ToastReceiver ^
  --es message "Sua mensagem" ^
  --ez long false
```

---

## 📦 Parâmetros

| Parâmetro | Tipo | Obrigatório | Descrição |
|---|---|---|---|
| `message` | string | ✅ | Texto a exibir na TV |
| `long` | boolean | ❌ | `true` = longo (~3.5s), `false` = curto (~2s). Padrão: `false` |

---

## 🗂️ Estrutura do projeto

```
app/
  src/
    main/
      AndroidManifest.xml          # declara o BroadcastReceiver
      java/com/aiwa/remote/toast/
        ToastReceiver.java         # recebe o broadcast e exibe o Toast
  build.gradle
build.gradle
settings.gradle
gradlew
.github/
  workflows/
    build.yml                      # compila o APK automaticamente
```

---

## 🔧 Como compilar manualmente

### Requisitos
- JDK 17+
- Android SDK (API 33)
- Gradle 8.5+

### Build
```bash
gradle assembleDebug
```

O APK gerado estará em:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Via GitHub Actions
Qualquer push na branch `main` dispara a build automaticamente. O APK fica disponível em **Actions → última build → Artifacts → aiwa-toast**.

---

## ⚙️ Compatibilidade

| Android | Suporte |
|---|---|
| 8.0 (API 26) | ✅ |
| 9.0 (API 28) | ✅ |
| 10 (API 29) | ✅ |
| 11 (API 30) | ✅ *(testado)* |
| 12+ (API 31+) | ✅ |

Testado em TV **AIWA Android TV** com Android 11.

---

## 📄 Licença

MIT — use, modifique e distribua à vontade.
